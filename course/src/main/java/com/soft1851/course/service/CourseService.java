package com.soft1851.course.service;

import com.soft1851.course.entity.Course;

import java.util.List;

/**
 * @Author su
 * @Date 2020/9/17
 * @Version 1.0
 */
public interface CourseService {

//    /**
//     * 通过ID查询单条数据
//     *
//     * @param id 主键
//     * @return 实例对象
//     */
//    CourseVo queryById(String id);


    /**
     * 通过 userid
     *
     * @param userId 用户id
     * @return List<CourseVo>
     */
    List<Course> queryList(String userId);
}
