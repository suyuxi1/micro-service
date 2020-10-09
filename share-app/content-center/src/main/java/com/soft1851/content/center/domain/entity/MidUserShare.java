package com.soft1851.content.center.domain.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

/**
 * @author su
 * @className MidUserShare
 * @Description TODO
 * @Date 2020/10/4
 * @Version 1.0
 **/

@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name = "mid_user_share")
@Builder
public class MidUserShare {


    @ApiModelProperty(name = "id", value = "id")
    private Integer id;

    @ApiModelProperty(name = "share_id", value = "share_id")
    private Integer shareId;


    @ApiModelProperty(name = "user_id", value = "user_id")
    private Integer userId;
}
