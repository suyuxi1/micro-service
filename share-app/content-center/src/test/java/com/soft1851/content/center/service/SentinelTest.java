package com.soft1851.content.center.service;

import org.springframework.web.client.RestTemplate;

/**
 * @author su
 * @className SentinelTest
 * @Description TODO
 * @Date 2020/10/6
 * @Version 1.0
 **/
public class SentinelTest {

    public static void main(String[] args) throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i < 100; i++) {
            String s = restTemplate.getForObject("http://localhost:9001/test/byResource", String.class);
            System.out.print(i + "ok");
            System.out.println(s);
//            Thread.sleep(100);
        }
    }

}
