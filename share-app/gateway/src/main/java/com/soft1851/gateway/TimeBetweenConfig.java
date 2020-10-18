package com.soft1851.gateway;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

/**
 * @author su
 * @className TimeBetweenConfig
 * @Description TODO
 * @Date 2020/10/9
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeBetweenConfig {

    private LocalTime start;
    private LocalTime end;
}
