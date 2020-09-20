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
public class User implements Serializable {
    private static final long serialVersionUID = -21117904334438215L;

    private String id;

    private String name;

    private String avatar;

    private String phone;

}
