package com.soft1851.content.center.auth;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 鉴权注解
 * @Author su
 * @Date 2020/10/13
 * @Version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckAuthorization {
    String value();
}
