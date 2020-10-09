package com.soft1851.content.center.controller;

import com.soft1851.content.center.domain.dto.ShareAuditDTO;
import com.soft1851.content.center.domain.entity.Share;
import com.soft1851.content.center.service.ShareService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author su
 * @className ShareAdminController
 * @Description TODO
 * @Date 2020/10/8
 * @Version 1.0
 **/

@RestController
@RequestMapping(value = "/admin/shares")
@Api(tags = "管理员接口", value = "提供管理员相关的Rest API")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareAdminController {

    private final ShareService shareService;

    /**
     * 审核异步操作接口
     *
     * @param id
     * @param shareAuditDTO
     * @return
     */
    @PutMapping("/audit/{id}")
    @ApiOperation(value = "投稿审核（异步）", notes = "通过指定id审核投稿")
    public Share auditById(@PathVariable Integer id, @RequestBody ShareAuditDTO shareAuditDTO) {
        //需要认证授权
        return this.shareService.auditById(id, shareAuditDTO);
    }


    /**
     * 审核同步操作
     *
     * @param id
     * @param shareAuditDTO
     * @return
     */
    @PutMapping("/audit1/{id}")
    @ApiOperation(value = "投稿审核（同步）", notes = "通过指定id审核投稿")
    public Share auditByFeign(@PathVariable Integer id, @RequestBody ShareAuditDTO shareAuditDTO) {
        //需要认证授权
        return this.shareService.auditById(id, shareAuditDTO);
    }


    /**
     * 通过AsyncRestTemplate调用
     *
     * @param id
     * @param shareAuditDTO
     * @return
     */
    @PutMapping("/audit2/{id}")
    @ApiOperation(value = "投稿审核（通过AsyncRestTemplate调用）", notes = "通过指定id审核投稿")
    public Share auditByAsyncRestTemplate(@PathVariable Integer id, @RequestBody ShareAuditDTO shareAuditDTO) {
        //需要认证授权
        return this.shareService.auditByAsyncRestTemplate(id, shareAuditDTO);
    }


}
