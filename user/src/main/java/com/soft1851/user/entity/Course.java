package com.soft1851.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author su
 * @Date 2020/9/17
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course implements Serializable {
    private static final long serialVersionUID = -45157472313496869L;

    private String id;

    private String userId;

    private String courseName;

    private Boolean isEnding;

    private String cover;

    private String className;

}
