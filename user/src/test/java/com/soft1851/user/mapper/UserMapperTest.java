package com.soft1851.user.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


/**
 * @Author su
 * @Date 2020/9/17
 * @Version 1.0
 */
@SpringBootTest
class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    void queryById() {
        System.out.println(userMapper.queryById("1"));
    }
}