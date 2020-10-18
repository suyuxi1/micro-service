package com.soft1851.content.center.service;

import com.github.pagehelper.PageInfo;
import com.soft1851.content.center.domain.dto.ExchangeDTO;
import com.soft1851.content.center.domain.dto.ShareAuditDTO;
import com.soft1851.content.center.domain.dto.ShareDTO;
import com.soft1851.content.center.domain.dto.ShareRequestDTO;
import com.soft1851.content.center.domain.entity.Share;

/**
 * @author Su
 * @className ShareService
 * @Description TODO
 * @Date 2020/9/29 15:51
 * @Version 1.0
 **/
public interface ShareService {



    /**
     * 通过Feign调用user-center服务
     *
     * @param id
     * @return
     */
    ShareDTO ShareDetailByFeign(Integer id);


    /**
     * 通过RestTemplate调用user-center服务
     *
     * @param id
     * @return
     */
    ShareDTO ShareDetail(Integer id);


//    List<ShareDTO> shareDetailList(Integer id);

    PageInfo<Share> query(String title, Integer pageNo, Integer pageSize, Integer userId);

    /**
     * 投稿
     * @param shareRequestDTO
     * @return Share
     */

    Share insertShare(ShareRequestDTO shareRequestDTO);

    /**
     * 异步审核操作
     *
     * @param id
     * @param shareAuditDTO
     * @return Share
     */
    Share auditById(Integer id, ShareAuditDTO shareAuditDTO);


    /**
     * 积分兑换资源
     *
     * @param exchangeDTO
     * @return Share
     */
    Share exchange(ExchangeDTO exchangeDTO);


    PageInfo<Share> myExchange(Integer pageNo, Integer pageSize, Integer userId);

    PageInfo<Share> myContribute(Integer pageNo, Integer pageSize, Integer userId);

//
//    /**
//     * 通过feign来调用用户中心接口
//     *
//     * @param id
//     * @param shareAuditDTO
//     * @return
//     */
//    Share auditByFeign(Integer id, ShareAuditDTO shareAuditDTO);

    /**
     * 通过AsyncRestTemplate 来调用用户中心接口
     *
     * @param id
     * @param shareAuditDTO
     * @return
     */
    Share auditByAsyncRestTemplate(Integer id, ShareAuditDTO shareAuditDTO);

    /**
     * 编辑投稿
     *
     * @param id
     * @param shareRequestDTO
     * @return
     */
    Share redactShare(Integer id, ShareRequestDTO shareRequestDTO);



}
