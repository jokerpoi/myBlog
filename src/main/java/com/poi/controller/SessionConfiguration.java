package com.poi.controller;

import com.poi.service.UserInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class SessionConfiguration implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(SessionConfiguration.class);

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

//        registry.addInterceptor(new UserInterceptor()).excludePathPatterns("/static/**");
        registry.addInterceptor(new UserInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/js/**","/css/**","/fonts/**","/icons/**");
    }
}
