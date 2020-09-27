package com.soft1851.user.center;/**
 * @Author su
 * @Date 2020/9/23
 * @Version 1.0
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author su
 * @className UserController
 * @Description TODO
 * @Date 2020/9/23
 * @Version 1.0
 **/

@RestController
@RequestMapping(value = "user")
@Slf4j
public class UserController {

    @GetMapping("/hello")
    public String getUser(){
        log.info("我被调用了...");
        return "hello spring cloud alibaba,this user-center";
    }

}
