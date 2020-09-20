package com.soft1851.user.service;

import com.soft1851.user.entity.CourseVo;

import java.util.List;

/**
 * @Author su
 * @Date 2020/9/17
 * @Version 1.0
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    List<CourseVo> queryById(String id);

    String getPython();

}
