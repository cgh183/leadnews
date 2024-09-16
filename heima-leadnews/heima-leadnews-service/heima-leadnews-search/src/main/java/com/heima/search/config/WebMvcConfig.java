package com.heima.search.config;

import com.heima.search.interceptor.ApTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //配置使拦截器生效，拦截所有的请求
        registry.addInterceptor(new ApTokenInterceptor()).addPathPatterns("/**");
    }
}
