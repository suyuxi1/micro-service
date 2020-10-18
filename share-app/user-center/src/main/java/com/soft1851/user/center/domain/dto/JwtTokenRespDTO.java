package com.soft1851.user.center.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author su
 * @className JwtTokenRespDTO
 * @Description TODO
 * @Date 2020/10/13
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class JwtTokenRespDTO {
    /**
     * token
     */
    private String token;

    /**
     * 过期时间
     */
    private  Long expirationTime;

}
