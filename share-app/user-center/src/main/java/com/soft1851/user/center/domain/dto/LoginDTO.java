package com.soft1851.user.center.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author su
 * @className LoginDto
 * @Description TODO
 * @Date 2020/10/13
 * @Version 1.0
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class LoginDTO {
    private String openId;
    private String loginCode;
    private String wxNickname;
    private String avatarUrl;
}
