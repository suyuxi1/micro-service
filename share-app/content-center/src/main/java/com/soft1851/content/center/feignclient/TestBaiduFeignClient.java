package com.soft1851.content.center.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Su
 * @className TestBaiduFeignClient
 * @Description TODO
 * @Date 2020/9/30 11:28
 * @Version 1.0
 **/
@FeignClient(name = "baidu", url = "http://www.baidu.com")
public interface TestBaiduFeignClient {
    @GetMapping()
    String index();
}
