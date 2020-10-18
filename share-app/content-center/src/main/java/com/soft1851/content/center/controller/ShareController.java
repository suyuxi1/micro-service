package com.soft1851.content.center.controller;

import com.soft1851.content.center.auth.CheckLogin;
import com.soft1851.content.center.configuration.ResponseResult;
import com.soft1851.content.center.domain.dto.ExchangeDTO;
import com.soft1851.content.center.domain.dto.ShareRequestDTO;
import com.soft1851.content.center.domain.entity.Share;
import com.soft1851.content.center.service.ShareService;
import com.soft1851.content.center.util.JwtOperator;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ShareController {

    @Resource
    private ShareService shareService;

    private final int MAX = 100;

    @GetMapping("/{id}")
    @ApiOperation(value = "查询指定id的分享详情", notes = "查询指定id的分享详情")
    public ResponseResult getDetailById(@PathVariable("id") Integer id) {
        return new ResponseResult(200, "成功", shareService.ShareDetailByFeign(id));
    }

    private final JwtOperator jwtOperator;
    @GetMapping("/query")
    @ApiOperation(value = "分享列表", notes = "分享列表")
    public List<Share> query(
            @RequestParam(required = false) String title,
            @RequestParam(required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestHeader(value = "X-Token", required = false) String token) {
        if (pageSize > MAX) {
            pageSize = MAX;
        }
        int userId = getUserIdFromToken(token);
        return this.shareService.query(title, pageNo, pageSize, userId).getList();
    }


    @PostMapping("/contribute")
    @ApiOperation(value = "投稿", notes = "投稿信息")
    public Share contribute(@RequestBody ShareRequestDTO shareRequestDTO) {
        return shareService.insertShare(shareRequestDTO);
    }


    @PutMapping("/contribute/{id}")
    @ApiOperation(value = "编辑投稿", notes = "根据id编辑投稿")
    public Share contributeById(@PathVariable Integer id, ShareRequestDTO shareRequestDTO) {
        return shareService.redactShare(id, shareRequestDTO);
    }

    @PostMapping("/exchange")
    @CheckLogin
    @ApiOperation(value = "积分兑换资源", notes = "积分兑换资源")
    public Share exchange(@RequestBody ExchangeDTO exchangeDTO) {
//        System.out.println(exchangeDTO + ">>>>>>>>>>>>");
        return this.shareService.exchange(exchangeDTO);
    }


    @GetMapping("/my-exchange")
//    @CheckLogin
    @ApiOperation(value = "我的兑换", notes = "我的兑换")
    public List<Share> myExchange(
            @RequestParam(required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestHeader(value = "X-Token", required = false) String token) {
        if (pageSize > MAX) {
            pageSize = MAX;
        }
        int userId = getUserIdFromToken(token);
        return this.shareService.myExchange(pageNo, pageSize, userId).getList();
    }


    @GetMapping("/my-contribute")
//    @CheckLogin
    @ApiOperation(value = "我的投稿", notes = "我的投稿")
    public List<Share> myContribute(
            @RequestParam(required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestHeader(value = "X-Token", required = false) String token) {
        if (pageSize > MAX) {
            pageSize = MAX;
        }
        int userId = getUserIdFromToken(token);
        log.info("我的投稿接口用户id为{}", userId);
        return this.shareService.myContribute(pageNo, pageSize, userId).getList();
    }


    /**
     * 封装一个从token中提取userId的方法
     *
     * @param token
     * @return userId
     */
    private int getUserIdFromToken(String token) {
        log.info(">>>>>>>>>>>token" + token);
        int userId = 0;
        String noToken = "no-token";
        if (!noToken.equals(token)) {
            Claims claims = this.jwtOperator.getClaimsFromToken(token);
            log.info(claims.toString());
            userId = (Integer) claims.get("id");
        } else {
            log.info("没有token");
        }
        return userId;
    }

    //    @GetMapping("/{id}")
//    public ResponseResult getDetailByIdList(@PathVariable("id") Integer id){
//        return new ResponseResult(200,"成功",shareService.shareDetailList(id));
//    }

}
