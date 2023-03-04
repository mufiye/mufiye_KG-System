package com.mufiye.config;

import com.mufiye.interceptor.AdminInterceptor;
import com.mufiye.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String userPrefix = "/users/";
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns(userPrefix+"*")
                .addPathPatterns("/system")
                .excludePathPatterns(userPrefix+"login")
                .excludePathPatterns(userPrefix+"register")
                .excludePathPatterns(userPrefix+"logout");
        registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns(userPrefix+"*")
                .excludePathPatterns(userPrefix+"login")
                .excludePathPatterns(userPrefix+"register")
                .excludePathPatterns(userPrefix+"logout")
                .excludePathPatterns(userPrefix+"getUser")
                .excludePathPatterns(userPrefix+"update");
    }

}
