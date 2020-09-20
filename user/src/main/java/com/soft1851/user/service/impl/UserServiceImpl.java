package com.soft1851.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.soft1851.user.entity.Course;
import com.soft1851.user.entity.CourseVo;
import com.soft1851.user.entity.User;
import com.soft1851.user.mapper.UserMapper;
import com.soft1851.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author su
 * @Date 2020/9/17
 * @Version 1.0
 */

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RestTemplate restTemplate;

    @Override
    public List<CourseVo> queryById(String id) {
        List<CourseVo> courseVoList = new ArrayList<>();
        User user = userMapper.queryById(id);
        String userId = user.getId();
        String json = restTemplate.getForObject("http://120.25.149.156:9001/course/select/{id}", String.class, userId);
        List<Course> courses = JSON.parseArray(json, Course.class);
        System.out.println(courses);
        assert courses != null;
        for (Course course : courses) {
            CourseVo courseVo = CourseVo.builder()
                    .id(course.getId())
                    .courseName(course.getCourseName())
                    .isEnding(course.getIsEnding())
                    .className(course.getClassName())
                    .cover(course.getCover())
                    .userId(user.getId())
                    .userName(user.getName())
                    .avatar(user.getAvatar())
                    .build();
            courseVoList.add(courseVo);
        }
        return courseVoList;
    }

    @Override
    public String getPython() {
        return restTemplate.getForObject("http://suyuxi.utools.club/hello", String.class);
    }
}
