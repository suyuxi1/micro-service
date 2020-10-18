package com.soft1851.content.center.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soft1851.content.center.domain.dto.*;
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
    public ShareDTO ShareDetailByFeign(Integer id) {
        // 获取分享实体
        Share share = this.shareMapper.selectByPrimaryKey(id);
        // 获得发布人id
        Integer userId = share.getUserId();
        // 解决之前的问题：
        // 1. 代码不可读
        // 2. 复杂的url难以维护：https://user-center/s?ie={ie}&f={f}&rsv_bp=1&rsv_idx=1&tn=baidu&wd=a&rsv_pq=c86459bd002cfbaa&rsv_t=edb19hb%2BvO%2BTySu8dtmbl%2F9dCK%2FIgdyUX%2BxuFYuE0G08aHH5FkeP3n3BXxw&rqlang=cn&rsv_enter=1&rsv_sug3=1&rsv_sug2=0&inputT=611&rsv_sug4=611
        // 3. 难以相应需求的变化，变化很没有幸福感
        // 4. 编程体验不统一
        //通过feign请求用户中心接口，获得发布人详情
        ResponseDTO responseDTO = this.userCenterFeignClient.findUserById(userId);
        UserDTO userDTO = convert(responseDTO);
        System.out.println(userDTO);
        System.out.println(userDTO);
        ShareDTO shareDTO = new ShareDTO();
        shareDTO.setShare(share);
        // 属性的装配
        //BeanUtils.copyProperties(share, shareDTO);
        assert userDTO != null;
        shareDTO.setWxNickname(userDTO.getWxNickname());
        return shareDTO;
    }

    @Override
    public ShareDTO ShareDetail(Integer id) {
        Share share = shareMapper.findById(id);
        User user = restTemplate.getForObject("http://user-center/users/{id}", User.class, share.getUserId());
        assert user != null;
        return ShareDTO.builder()
                .share(share)
                .wxNickname(user.getWxNickname())
                .build();
    }

//


    /**
     * 分页查询某个用户首页可见的资源列表
     *
     * @param title
     * @param pageNo
     * @param pageSize
     * @param userId
     * @return pageInfo
     */
    @Override
    public PageInfo<Share> query(String title, Integer pageNo, Integer pageSize, Integer userId) {
        log.info("用户id{}" , userId);
        //启动分页
        PageHelper.startPage(pageNo, pageSize);
        //构造查询实例
        Example example = new Example(Share.class);
        example.setOrderByClause("id DESC");
        Example.Criteria criteria = example.createCriteria();
        //如标题关键字不空，则加上模糊查询条件，否则结果即所有数据
        if (StringUtil.isNotEmpty(title)) {
            criteria.andLike("title", "%" + title + "%");
        }
        criteria.andEqualTo("showFlag", true)
                .andEqualTo("auditStatus", "PASS");

        //执行按条件查询
        List<Share> shares = this.shareMapper.selectByExample(example);
        log.info("share列表{}", shares);
        //处理后的Share数据列表
        List<Share> sharesDeal;
        // 1. 如果用户未登录，那么downloadUrl全部设为null
        if (userId == null) {
            sharesDeal = shares.stream()
                    .peek(share -> share.setDownloadUrl(null))
                    .collect(Collectors.toList());
        }
        // 2. 如果用户登录了，那么查询一下mid_user_share，如果没有数据，那么这条share的downloadUrl也设为null
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
                        log.info("用户兑换信息{}", midUserShare);
                        if (midUserShare == null) {
                            log.info("进入置空操作");
                            share.setDownloadUrl(null);
                        }
                    })
                    .collect(Collectors.toList());
        }
        log.info("返回list{}", sharesDeal);
        return new PageInfo<>(sharesDeal);
    }

    /**
     * 查询待审核状态的shares列表
     *
     * @return List<Share>
     */
    public List<Share> querySharesNotYet() {
        Example example = new Example(Share.class);
        example.setOrderByClause("id DESC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("showFlag", false)
                .andEqualTo("auditStatus", "NOT_YET");
        return this.shareMapper.selectByExample(example);
    }

    @Override
    public Share insertShare(ShareRequestDTO shareRequestDTO) {
        log.info("投稿参数{}", shareRequestDTO);
        Share share = new Share();
        BeanUtils.copyProperties(shareRequestDTO, share);
        share.setUserId(1);
        share.setCreateTime(LocalDateTime.now());
        share.setUpdateTime(LocalDateTime.now());
        share.setIsOriginal(shareRequestDTO.getIsOriginal());
        share.setCover("https://dss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3892521478,1695688217&fm=26&gp=0.jpg");
        share.setBuyCount(0);
        share.setDownloadUrl(shareRequestDTO.getDownloadUrl());
        share.setShowFlag(true);
        share.setAuditStatus("NOT_YET");
        share.setReason("");
        shareMapper.insert(share);
        return share;
    }

    private final RocketMQTemplate rocketMQTemplate;
    @Override
    public Share auditById(Integer id, ShareAuditDTO shareAuditDTO) {
        // 1. 查询share是否存在，不存在或者当前的audit_status != NOT_YET，那么抛异常
        Share share = this.shareMapper.selectByPrimaryKey(id);
        if (share == null) {
            throw new IllegalArgumentException("参数非法！该分享不存在！");
        }
        if (Objects.equals("PASS", share.getAuditStatus())) {
            throw new IllegalArgumentException("参数非法！该分享已审核通过或审核不通过！");
        }
        //2.审核资源，将状态改为PASS或REJECT
        //这个API的主要流程是审核，所以不需要等更新积分的结果返回，可以将加积分改为异步
        share.setAuditStatus(shareAuditDTO.getAuditStatusEnum().toString());
        share.setReason(shareAuditDTO.getReason());
        this.shareMapper.updateByPrimaryKey(share);

        // 3. 如果是PASS，那么发送消息给rocketmq，让用户中心去消费，并为发布人添加积分
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
    public Share exchange(ExchangeDTO exchangeDTO) {
        log.info("兑换获取到的数据{}", exchangeDTO);
        int userId = exchangeDTO.getUserId();
        int shareId = exchangeDTO.getShareId();
        // 1. 根据id查询share，校验是否存在
        Share share = this.shareMapper.selectByPrimaryKey(shareId);
        if (share == null) {
            throw new IllegalArgumentException("该分享不存在！");
        }
        Integer price = share.getPrice();

        // 2. 如果当前用户已经兑换过该分享，则直接返回
        MidUserShare midUserShare = this.midUserShareMapper.selectOne(
                MidUserShare.builder()
                        .shareId(shareId)
                        .userId(userId)
                        .build()
        );
        if (midUserShare != null) {
            log.info("判断兑换");
            return share;
        }

        // 3. 根据当前登录的用户id，查询积分是否够
        //这里一定要注意通过调用户中心接口得到的返回值，外面已经封装了一层了，要解析才能拿到真正的用户数据
        ResponseDTO responseDTO = this.userCenterFeignClient.findUserById(userId);
        UserDTO userDTO = convert(responseDTO);
        System.out.println(userDTO);
        if (price > userDTO.getBonus()) {
            throw new IllegalArgumentException("用户积分不够！");
        }

        // 4. 扣积分
        this.userCenterFeignClient.updateBonus(
                UserAddBonusMsgDTO.builder()
                        .userId(userId)
                        .bonus(price * -1)
                        .build()
        );
        //5. 向mid_user_share表里插入一条数据
        this.midUserShareMapper.insert(
                MidUserShare.builder()
                        .userId(userId)
                        .shareId(shareId)
                        .build()
        );
        return share;
    }
    /**
     * 我的兑换
     *
     * @param pageNo
     * @param pageSize
     * @param userId
     * @return sharesDeal
     */
    @Override
    public PageInfo<Share> myExchange(Integer pageNo, Integer pageSize, Integer userId) {
        PageHelper.startPage(pageNo, pageSize);
        Example example = new Example(Share.class);
        example.setOrderByClause("id DESC");
        List<Share> shares = this.shareMapper.selectByExample(example);
        //处理后的Share数据列表
        List<Share> sharesDeal;
        //过滤出在中间表存在记录的数据
        sharesDeal = shares.stream()
                .filter(share -> this.midUserShareMapper.selectOne(
                        MidUserShare.builder()
                                .userId(userId)
                                .shareId(share.getId())
                                .build()) != null
                )
                .collect(Collectors.toList());
        return new PageInfo<>(sharesDeal);
    }


    /**
     * 我的投稿
     * @param pageNo
     * @param pageSize
     * @param userId
     * @return shares
     */

    @Override
    public PageInfo<Share> myContribute(Integer pageNo, Integer pageSize, Integer userId) {
        PageHelper.startPage(pageNo, pageSize);
        Example example = new Example(Share.class);
        example.setOrderByClause("id DESC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        List<Share> shares = this.shareMapper.selectByExample(example);
        return new PageInfo<>(shares);
    }



    /**
     * 将统一的返回响应结果转换为UserDTO类型
     *
     * @param responseDTO
     * @return
     */
    private UserDTO convert(ResponseDTO responseDTO) {
        ObjectMapper mapper = new ObjectMapper();
        UserDTO userDTO = null;
        try {
            String json = mapper.writeValueAsString(responseDTO.getData());
            userDTO = mapper.readValue(json, UserDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        log.info("最后返回");
        return userDTO;
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



//    @Override
//    public List<ShareDTO> shareDetailList(Integer id) {
//        List<Share> shares = shareMapper.queryById(id);
//        List<ShareDTO> shareDtoList = new ArrayList<>();
//        for (Share share : shares) {
//            User user = restTemplate.getForObject("http://user-center/users/{id}", User.class, share.getUserId());
//            assert user != null;
//            log.info("用户id,{}", user.getId());
//            ShareDTO shareDto = ShareDTO.builder()
//                    .share(share)
//                    .wxNickname(user.getWxNickname())
//                    .build();
//            shareDtoList.add(shareDto);
//        }
//        return shareDtoList;
//    }

}
