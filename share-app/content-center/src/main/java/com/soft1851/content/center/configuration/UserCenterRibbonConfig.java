package com.soft1851.content.center.configuration;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;
import ribbonconfig.RibbonConfiguration;

/**
 * @author su
 * @className RibbonConfig
 * @Description TODO
 * @Date 2020/9/25
 * @Version 1.0
 **/
@Configuration
@RibbonClient(name = "user-center", configuration = RibbonConfiguration.class)
public class UserCenterRibbonConfig {

}
