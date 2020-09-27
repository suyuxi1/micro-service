package com.soft1851.content.center.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * @author su
 * @className HelloController
 * @Description TODO
 * @Date 2020/9/22
 * @Version 1.0
 **/

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping()
    public List<ServiceInstance> getUserCenter(){
        return discoveryClient.getInstances("user-center");
    }

    @GetMapping("/call/hello")
    public String callUserCenter() {
        //用户中心所有的实例信息
        List<ServiceInstance> instances = discoveryClient.getInstances("user-center");
        //stream编程、Lambda表达式、函数式编程
        //理解这段代码的含义？它实现了什么功能？
        List<String> targetUrls = instances.stream()
                .map(instance -> instance.getUri().toString() + "/user/hello")
                .collect(Collectors.toList());
        int i = ThreadLocalRandom.current().nextInt(targetUrls.size());


//
//        int i = RandomUtils.nextInt(instances.size());
//        log.info("索引为：{}",i);
//        String targetUrl = instances.get(i).getUri().toString() + "/user/hello";
//        String targetUrl = instances.stream()
//                .map(instance -> instance.getUri().toString() + "/user/hello")
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("当前没有实例！"));
        log.info("请求目标地址：{}",targetUrls.get(i));
        return restTemplate.getForObject(targetUrls.get(i), String.class);
    }

    @GetMapping("/call/ribbon")
    public String callByRibbon(){
        return restTemplate.getForObject("http://user-center/user/hello", String.class);
    }
}
