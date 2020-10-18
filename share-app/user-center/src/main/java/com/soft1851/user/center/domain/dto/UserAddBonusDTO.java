package com.soft1851.user.center.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author su
 * @className UserAddBonusDTO
 * @Description TODO
 * @Date 2020/10/18
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户添加积分数据传输对象")
public class UserAddBonusDTO {
    @ApiModelProperty(name = "userId", value = "用户id")
    private Integer userId;

    @ApiModelProperty(name = "bonus", value = "积分数")
    private Integer bonus;
}
