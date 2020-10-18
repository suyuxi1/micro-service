package com.soft1851.user.center.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author su
 * @className UserRespDTO
 * @Description TODO
 * @Date 2020/10/13
 * @Version 1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserRespDTO {
    /**
     * id
     */
    private Integer id;

    /**
     * 头像地址
     */
    private String avatarUrl;

    /**
     * 积分
     */
    private Integer bonus;

    /**
     * 微信名称
     */
    private String wxNickname;

    private String roles;
}

