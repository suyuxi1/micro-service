package com.soft1851.content.center.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author su
 * @className ExchangeDTO
 * @Description TODO
 * @Date 2020/10/14
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExchangeDTO {
    private Integer userId;
    private Integer shareId;
}
