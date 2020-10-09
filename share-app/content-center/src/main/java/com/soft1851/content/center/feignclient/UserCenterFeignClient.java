package com.soft1851.content.center.feignclient;

import com.soft1851.content.center.domain.dto.UserAddBonusMsgDTO;
import com.soft1851.content.center.domain.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Su
 * @className UserCenterFeignClient
 * @Description TODO
 * @Date 2020/9/29 19:39
 * @Version 1.0
 **/
//@FeignClient(name = "user-center", configuration = UserCenterFeignConfiguration.class)
@FeignClient(name = "user-center")
public interface UserCenterFeignClient {

    @GetMapping("/users/{id}")
    User findUserById(@PathVariable Integer id);


    @PostMapping("/users/bonus")
    int updateBonus(@RequestBody UserAddBonusMsgDTO userAddBonusMsgDTO);

}
