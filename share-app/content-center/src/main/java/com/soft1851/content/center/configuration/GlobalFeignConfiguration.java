package com.soft1851.content.center.configuration;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @author su
 * @className GlobalFeignConfiguration
 * @Description TODO
 * @Date 2020/9/30
 * @Version 1.0
 **/
public class GlobalFeignConfiguration {

    @Bean
    public Logger.Level level() {
        return Logger.Level.FULL;
    }

}
