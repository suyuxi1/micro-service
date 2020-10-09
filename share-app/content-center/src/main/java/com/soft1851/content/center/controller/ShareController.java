package com.soft1851.content.center.controller;

import com.soft1851.content.center.configuration.ResponseResult;
import com.soft1851.content.center.domain.dto.ShareRequestDTO;
import com.soft1851.content.center.domain.entity.Share;
import com.soft1851.content.center.service.ShareService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author su
 * @className ShareController
 * @Description TODO
 * @Date 2020/9/29
 * @Version 1.0
 **/

@RestController
@RequestMapping(value = "/shares")
@Api(tags = "分享接口", value = "提供分享相关的Rest API")
public class ShareController {

    @Resource
    private ShareService shareService;

    @GetMapping("/{id}")
    @ApiOperation(value = "查询指定id的分享详情", notes = "查询指定id的分享详情")
    public ResponseResult getDetailById(@PathVariable("id") Integer id) {
        return new ResponseResult(200, "成功", shareService.ShareDetailByFeign(id));
    }

    @GetMapping("/query")
    @ApiOperation(value = "分享列表", notes = "分享列表")
    public List<Share> query(
            @RequestParam(required = false) String title,
            @RequestParam(required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) Integer userId) throws Exception {
        if (pageSize > 100) {
            pageSize = 100;
        }
        return this.shareService.query(title, pageNo, pageSize, userId).getList();
    }


    @PostMapping("/contribute")
    @ApiOperation(value = "投稿", notes = "投稿信息")
    public Share contribute(ShareRequestDTO shareRequestDTO) {
        return shareService.insertShare(shareRequestDTO);
    }


    @PutMapping("/contribute/{id}")
    @ApiOperation(value = "编辑投稿", notes = "根据id编辑投稿")
    public Share contributeById(@PathVariable Integer id, ShareRequestDTO shareRequestDTO) {
        return shareService.redactShare(id, shareRequestDTO);
    }


    //    @GetMapping("/{id}")
//    public ResponseResult getDetailByIdList(@PathVariable("id") Integer id){
//        return new ResponseResult(200,"成功",shareService.shareDetailList(id));
//    }

}
