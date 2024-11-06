package com.baijiu.Baijiu_Back.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


// 使用注解说明是全局配置类
@Configuration
public class WebConfig implements WebMvcConfigurer { // 继承跨域请求的类

    @Override
    public void addCorsMappings(CorsRegistry registry) { // 跨域处理的方法
        registry.addMapping("/**") // 任意访问都允许跨域
//                .allowedOrigins("http://172.27.0.40:9000")  // 允许所有域的跨域请求
                .allowedOrigins("http://localhost:8080")  // 允许所有域的跨域请求
//                .allowedOrigins("*")  // 允许所有域的跨域请求
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE") // 跨域请求类型
//                .allowedOriginPatterns("*")
                .maxAge(3600) // 超时时间
                .allowCredentials(true)// 允许携带信息
                .allowedHeaders("*")
                .exposedHeaders("*")
        ; // 允许携带信息

    }


    //静态资源图片的路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("classpath:/upload/");
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}

