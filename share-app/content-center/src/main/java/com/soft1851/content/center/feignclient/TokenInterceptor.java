package com.soft1851.content.center.feignclient;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author su
 * @className TokenInterceptor
 * @Description Token传递拦截器
 * @Date 2020/10/13
 * @Version 1.0
 **/
public class TokenInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        //1.获取到token
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("X-Token");
        //2.将token传递
        if (StringUtils.isNotBlank(token)){
            template.header("X-Token", token);
        }
    }
}
