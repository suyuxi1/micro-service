package com.soft1851.course.service.impl;

import com.soft1851.course.entity.Course;
import com.soft1851.course.mapper.CourseMapper;
import com.soft1851.course.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author su
 * @Date 2020/9/17
 * @Version 1.0
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseMapper courseMapper;

    @Override
    public List<Course> queryList(String userId) {
        return courseMapper.queryList(userId);
    }
}

