package com.soft1851.course.controller;

import com.soft1851.course.entity.Course;
import com.soft1851.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author su
 * @Date 2020/9/17
 * @Version 1.0
 */

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseController {

//    @Autowired
    private final CourseService courseService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 全部数据
     */
    @GetMapping("/select/{id}")
    public List<Course> selectOne(@PathVariable("id") String id) {
        return this.courseService.queryList(id);
    }
}
