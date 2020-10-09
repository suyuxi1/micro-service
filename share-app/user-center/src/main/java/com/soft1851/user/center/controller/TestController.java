package com.soft1851.user.center.controller;

import com.soft1851.user.center.domain.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author su
 * @className TestController
 * @Description TODO
 * @Date 2020/9/30
 * @Version 1.0
 **/

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @GetMapping("/q")
    public User getUser(User user) {
        return user;
    }
}
