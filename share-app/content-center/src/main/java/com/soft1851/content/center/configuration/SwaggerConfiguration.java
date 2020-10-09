package com.soft1851.content.center.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @author su
 * @className SwaggerConfiguration
 * @Description TODO
 * @Date 2020/10/4
 * @Version 1.0
 **/
@EnableSwagger2
@Configuration
public class SwaggerConfiguration {
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "share-app Swagger文档",
                "github地址: https://github.com/suyuxi1/micro-service",
                "API V1.0",
                "Terms of service",
                new Contact("苏玉溪", "", "2296887348@qq.com"),
                "Apache", "http://www.apache.org/", Collections.emptyList()
        );
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.soft1851.content.center"))
                .build()
                .apiInfo(apiInfo());
    }

}
