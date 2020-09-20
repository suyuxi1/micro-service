package com.soft1851.user.controller;

import com.soft1851.user.entity.CourseVo;
import com.soft1851.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author su
 * @Date 2020/9/17
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectById/{id}")
    public List<CourseVo> selectList(@PathVariable("id") String id) {
        return userService.queryById(id);
    }

    @GetMapping("/python")
    public String getPython() {
        return userService.getPython();
    }

}