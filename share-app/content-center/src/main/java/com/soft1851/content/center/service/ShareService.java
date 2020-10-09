package com.soft1851.content.center.service;

import com.github.pagehelper.PageInfo;
import com.soft1851.content.center.domain.dto.ShareAuditDTO;
import com.soft1851.content.center.domain.dto.ShareDto;
import com.soft1851.content.center.domain.dto.ShareRequestDTO;
import com.soft1851.content.center.domain.entity.Share;

import java.util.List;

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
    ShareDto ShareDetailByFeign(Integer id);


    /**
     * 通过RestTemplate调用user-center服务
     *
     * @param id
     * @return
     */
    ShareDto ShareDetail(Integer id);


    List<ShareDto> shareDetailList(Integer id);

    PageInfo<Share> query(String title, Integer pageNo, Integer pageSize, Integer userId);

    Share insertShare(ShareRequestDTO shareRequestDTO);

    /**
     * 审核异步操作
     *
     * @param id
     * @param shareAuditDTO
     * @return Share
     */
    Share auditById(Integer id, ShareAuditDTO shareAuditDTO);

    /**
     * 通过feign来调用用户中心接口
     *
     * @param id
     * @param shareAuditDTO
     * @return
     */
    Share auditByFeign(Integer id, ShareAuditDTO shareAuditDTO);

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
