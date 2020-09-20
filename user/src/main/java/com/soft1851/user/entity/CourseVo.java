package com.soft1851.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author su
 * @Date 2020/9/17
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseVo {

    private String id;

    private String userId;

    private String avatar;

    private String courseName;

    private String userName;

    private Boolean isEnding;

    private String cover;

    private String className;
}
