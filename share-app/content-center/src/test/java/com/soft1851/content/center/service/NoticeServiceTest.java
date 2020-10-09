package com.soft1851.content.center.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Author su
 * @Date 2020/10/4
 * @Version 1.0
 */

@SpringBootTest
class NoticeServiceTest {

    @Resource
    private NoticeService noticeService;

    @Test
    void getLatest() {
        System.out.println(noticeService.getLatest());
    }
}