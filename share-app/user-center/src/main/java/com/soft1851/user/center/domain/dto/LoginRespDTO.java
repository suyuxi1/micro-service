package com.soft1851.user.center.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author su
 * @className LoginResDTO
 * @Description TODO
 * @Date 2020/10/13
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRespDTO {

    /**
     * 用户信息
     */
    private UserRespDTO user;

    /**
     * token数据
     */
    private JwtTokenRespDTO token;
}
