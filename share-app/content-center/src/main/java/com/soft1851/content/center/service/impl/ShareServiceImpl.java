package com.soft1851.content.center.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soft1851.content.center.domain.dto.ShareAuditDTO;
import com.soft1851.content.center.domain.dto.ShareDto;
import com.soft1851.content.center.domain.dto.ShareRequestDTO;
import com.soft1851.content.center.domain.dto.UserAddBonusMsgDTO;
import com.soft1851.content.center.domain.entity.MidUserShare;
import com.soft1851.content.center.domain.entity.Share;
import com.soft1851.content.center.domain.entity.User;
import com.soft1851.content.center.feignclient.UserCenterFeignClient;
import com.soft1851.content.center.mapper.MidUserShareMapper;
import com.soft1851.content.center.mapper.ShareMapper;
import com.soft1851.content.center.service.ShareService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author su
 * @className ShareServiceImpl
 * @Description TODO
 * @Date 2020/9/29
 * @Version 1.0
 **/
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareServiceImpl implements ShareService {

    private final ShareMapper shareMapper;
    private final MidUserShareMapper midUserShareMapper;
    private final RestTemplate restTemplate;
    private final UserCenterFeignClient userCenterFeignClient;

    @Override
    public ShareDto ShareDetailByFeign(Integer id) {
        Share share = shareMapper.selectByPrimaryKey(id);
        log.info("返回对象数据{}", share);
        User user = this.userCenterFeignClient.findUserById(share.getUserId());
        return ShareDto.builder()
                .share(share)
                .wxNickname(user.getWxNickname())
                .build();
    }

    @Override
    public ShareDto ShareDetail(Integer id) {
        Share share = shareMapper.findById(id);
        User user = restTemplate.getForObject("http://user-center/users/{id}", User.class, share.getUserId());
        assert user != null;
        return ShareDto.builder()
                .share(share)
                .wxNickname(user.getWxNickname())
                .build();
    }

    @Override
    public List<ShareDto> shareDetailList(Integer id) {
        List<Share> shares = shareMapper.queryById(id);
        List<ShareDto> shareDtoList = new ArrayList<>();
        for (Share share : shares) {
            User user = restTemplate.getForObject("http://user-center/users/{id}", User.class, share.getUserId());
            assert user != null;
            log.info("用户id,{}", user.getId());
            ShareDto shareDto = ShareDto.builder()
                    .share(share)
                    .wxNickname(user.getWxNickname())
                    .build();
            shareDtoList.add(shareDto);
        }
        return shareDtoList;
    }

    @Override
    public PageInfo<Share> query(String title, Integer pageNo, Integer pageSize, Integer userId) {

        //启动分页
        PageHelper.startPage(pageNo, pageSize);
        //构造查询实例
        Example example = new Example(Share.class);
        Example.Criteria criteria = example.createCriteria();
        //如标题关键字不空，则加上模糊查询条件，否则结果即所有数据
        if (StringUtil.isNotEmpty(title)) {
            criteria.andLike("title", "%" + title + "%");
        }
        //执行按条件查询
        List<Share> shares = this.shareMapper.selectByExample(example);
        //处理后的Share数据列表
        List<Share> sharesDeal;
        //1.如果用户未登录，那么downloadUrL全部设为null
        if (userId == null) {
            sharesDeal = shares.stream()
                    .peek(share -> {
                        share.setDownloadUrl(null);
                    })
                    .collect(Collectors.toList());
        }
        //2.如果用户登录了，那么查询一下mid_user_share,如果没有数据，那么这条share的downloadUrl也设为null
        //只有自己分享的资源才能直接看到下载链接，否则显示"兑换"
        else {
            sharesDeal = shares.stream()
                    .peek(share -> {
                        MidUserShare midUserShare = this.midUserShareMapper.selectOne(
                                MidUserShare.builder()
                                        .userId(userId)
                                        .shareId(share.getId())
                                        .build()
                        );
                        if (midUserShare == null) {
                            share.setDownloadUrl(null);
                        }
                    }).collect(Collectors.toList());
        }
        return new PageInfo<>(sharesDeal);
    }

    @Override
    public Share insertShare(ShareRequestDTO shareRequestDTO) {
        Share share = new Share();
        BeanUtils.copyProperties(shareRequestDTO, share);
        share.setUserId(1);
        share.setCreateTime(LocalDateTime.now());
        share.setUpdateTime(LocalDateTime.now());
        share.setCover("cover");
        share.setBuyCount(0);
        share.setShowFlag(true);
        share.setAuditStatus("NOT_YET");
        share.setReason("");
        shareMapper.insert(share);
        return share;
    }

    private final RocketMQTemplate rocketMQTemplate;

    @Override
    public Share auditById(Integer id, ShareAuditDTO shareAuditDTO) {
        //1.查询share是否存在，不存在或者当前的audit_status != NOT_YET,那么抛异常
        Share share = this.shareMapper.selectByPrimaryKey(id);
        if (share == null) {
            throw new IllegalArgumentException("参数非法！该分享不存在！");
        }
        if (!Objects.equals("NOT_YET", share.getAuditStatus())) {
            throw new IllegalArgumentException("参数非法！该分享已审核通过或审核不通过！");
        }
        //2.审核资源，将状态改为PASS或REJECT
        //这个API的主要流程是审核，所以不需要等更新积分的返回，可将积分改为异步
        share.setAuditStatus(shareAuditDTO.getAuditStatusEnum());
        this.shareMapper.updateByPrimaryKey(share);
        //3.如果是PASS，那么发送消息给rocketmq，让用户中心去消费，并为发布人添加积分
        if ("PASS".equals(shareAuditDTO.getAuditStatusEnum())) {
            this.rocketMQTemplate.convertAndSend(
                    "add-bonus",
                    UserAddBonusMsgDTO.builder()
                            .userId(share.getUserId())
                            .bonus(50)
                            .build());
        }
        return share;
    }

    @Override
    public Share auditByFeign(Integer id, ShareAuditDTO shareAuditDTO) {
        //1.查询share是否存在，不存在或者当前的audit_status != NOT_YET,那么抛异常
        Share share = this.shareMapper.selectByPrimaryKey(id);
        if (share == null) {
            throw new IllegalArgumentException("参数非法！该分享不存在！");
        }
        if (!Objects.equals("NOT_YET", share.getAuditStatus())) {
            throw new IllegalArgumentException("参数非法！该分享已审核通过或审核不通过！");
        }
        //2.审核资源，将状态改为PASS或REJECT
        //这个API的主要流程是审核，所以不需要等更新积分的返回，可将积分改为异步
        share.setAuditStatus(shareAuditDTO.getAuditStatusEnum());
        this.shareMapper.updateByPrimaryKey(share);
        //通过feign来调用user-center
        if ("PASS".equals(shareAuditDTO.getAuditStatusEnum())) {
            int i = this.userCenterFeignClient.updateBonus(UserAddBonusMsgDTO.builder().userId(share.getUserId()).bonus(50).build());
            log.info("接口调用返回结果{}", i);
        }
        return share;
    }

    private final AsyncRestTemplate asyncRestTemplate;

    @Override
    public Share auditByAsyncRestTemplate(Integer id, ShareAuditDTO shareAuditDTO) {
        //1.查询share是否存在，不存在或者当前的audit_status != NOT_YET,那么抛异常
        Share share = this.shareMapper.selectByPrimaryKey(id);
        if (share == null) {
            throw new IllegalArgumentException("参数非法！该分享不存在！");
        }
        if (!Objects.equals("NOT_YET", share.getAuditStatus())) {
            throw new IllegalArgumentException("参数非法！该分享已审核通过或审核不通过！");
        }
        //2.审核资源，将状态改为PASS或REJECT
        //这个API的主要流程是审核，所以不需要等更新积分的返回，可将积分改为异步
        share.setAuditStatus(shareAuditDTO.getAuditStatusEnum());
        this.shareMapper.updateByPrimaryKey(share);
//        ListenableFuture<ResponseEntity<String>> entity = asyncRestTemplate.getForEntity("http://user-center/users/bonus", String.class);
        if ("PASS".equals(shareAuditDTO.getAuditStatusEnum())) {
            asyncRestTemplate.getForEntity("http://user-center/users/bonus", String.class, UserAddBonusMsgDTO.builder().userId(share.getUserId()).bonus(50).build());
        }
        return share;
    }

    @Override
    public Share redactShare(Integer id, ShareRequestDTO shareRequestDTO) {
        Share share = shareMapper.selectByPrimaryKey(id);
        share.setAuthor(shareRequestDTO.getAuthor());
        share.setDownloadUrl(shareRequestDTO.getDownloadUrl());
        share.setIsOriginal(shareRequestDTO.getIsOriginal());
        share.setPrice(shareRequestDTO.getPrice());
        share.setSummary(shareRequestDTO.getSummary());
        share.setTitle(shareRequestDTO.getTitle());
        shareMapper.updateByPrimaryKey(share);
        return share;
    }

}
