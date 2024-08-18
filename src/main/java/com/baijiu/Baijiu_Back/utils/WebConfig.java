package com.baijiu.Baijiu_Back.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


// 使用注解说明是全局配置类
@Configuration
public class WebConfig implements WebMvcConfigurer { // 继承跨域请求的类

    @Override
    public void addCorsMappings(CorsRegistry registry) { // 跨域处理的方法
        registry.addMapping("/**") // 任意访问都允许跨域
                .allowedOriginPatterns("*") // 跨域来源
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE") // 跨域请求类型
                .maxAge(3600) // 超时时间
                .allowCredentials(true)// 允许携带信息
                .allowedHeaders("*")
                .exposedHeaders("*")
        ; // 允许携带信息

    }
}

