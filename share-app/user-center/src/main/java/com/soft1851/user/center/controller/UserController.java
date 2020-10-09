package com.soft1851.user.center.controller;/**
 * @Author su
 * @Date 2020/9/23
 * @Version 1.0
 */

import com.soft1851.user.center.domain.dto.UserAddBonusMsgDto;
import com.soft1851.user.center.domain.entity.User;
import com.soft1851.user.center.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author su
 * @className UserController
 * @Description TODO
 * @Date 2020/9/23
 * @Version 1.0
 **/

@RestController
@RequestMapping(value = "users")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/hello")
    public String hello() {
        log.info("我被调用了...");
        return "hello spring cloud alibaba,this user-center";
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Integer id) {
        log.info("我被调用了...");
        return userService.findUserById(id);
    }

    @PostMapping("/bonus")
    public int updateBonus(@RequestBody UserAddBonusMsgDto userAddBonusMsgDto) {
        log.info("我被调用了...");
        return userService.updateBonus(userAddBonusMsgDto);
    }


}
