package com.soft1851.content.center.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.soft1851.content.center.domain.dto.UserDTO;
import com.soft1851.content.center.domain.entity.User;
import com.soft1851.content.center.feignclient.TestBaiduFeignClient;
import com.soft1851.content.center.feignclient.TestUserCenterFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestController {


    private final DiscoveryClient discoveryClient;


    private final RestTemplate restTemplate;


    @GetMapping()
    public List<ServiceInstance> getUserCenter() {
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
        log.info("请求目标地址：{}", targetUrls.get(i));
        return restTemplate.getForObject(targetUrls.get(i), String.class);
    }

    @GetMapping("/call/ribbon")
    public String callByRibbon() {
        return restTemplate.getForObject("http://user-center/user/hello", String.class);
    }

    @Resource
    private TestUserCenterFeignClient testUserCenterFeignClient;

    @GetMapping("/test-q")
    public UserDTO query(UserDTO userDto) {
        return testUserCenterFeignClient.getUser(userDto);
    }

    @Resource
    private TestBaiduFeignClient testBaiduFeignClient;

    @GetMapping("/baidu")
    public String baiduIndex() {
        return this.testBaiduFeignClient.index();
    }

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public String byResource() {
        return "按名称限流";
    }

    public String handleException(BlockException blockException) {
        RestTemplate restTemplate = new RestTemplate();
        return Objects.requireNonNull(restTemplate.getForObject("http://localhost:9001/notice/one", Object.class)).toString();
    }

//
//    @Autowired
//    private MD5Service md5Service;

//    @RequestMapping("/test")
//    public String getMD5() {
//        return "MD5加密结果为：" + md5Service.getMD5("mypassword");
//    }


    @Resource
    private AsyncRestTemplate asyncRestTemplate;

    @GetMapping("/asyncRestTemplate")
    public String demo1() {
        String url = "http://localhost:9103/users/1";
        log.info("Start");
        ListenableFuture<ResponseEntity<User>> entity = asyncRestTemplate.getForEntity(url, User.class);
        log.info("用户返回值{}", entity.toString());
        entity.addCallback(result -> {
            assert result != null;
            log.info(Objects.requireNonNull(result.getBody()).getWxNickname());
            log.info("A");
        }, ex -> log.info("B"));
        log.info("C");
        return "End";
    }


}
