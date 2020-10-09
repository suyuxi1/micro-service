package com.soft1851.user.center.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Author su
 * @Date 2020/9/29
 * @Version 1.0
 */

@SpringBootTest
class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    void findUserById() {
        System.out.println(userService.findUserById(1));
    }
}