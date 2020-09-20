package com.soft1851.course.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author su
 * @Date 2020/9/17
 * @Version 1.0
 */
@SpringBootTest
class CourseMapperTest {

    @Resource
    private CourseMapper courseMapper;
    @Test
    void queryList() {
        System.out.println(courseMapper.queryList("1").size());
    }
}