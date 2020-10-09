package com.soft1851.user.center.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author su
 * @className UserAddBonusMsgDto
 * @Description TODO
 * @Date 2020/10/8
 * @Version 1.0
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddBonusMsgDto {

    private Integer userId;

    private Integer bonus;

}
