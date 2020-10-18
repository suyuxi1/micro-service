package com.soft1851.content.center.feignclient;

import com.soft1851.content.center.domain.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Su
 * @className TestUserCenterFeignClient
 * @Description TODO
 * @Date 2020/9/30 10:59
 * @Version 1.0
 **/

@FeignClient(name = "user-center")
public interface TestUserCenterFeignClient {
    @GetMapping("/test/q")
    UserDTO getUser(@SpringQueryMap UserDTO userDto);

}
