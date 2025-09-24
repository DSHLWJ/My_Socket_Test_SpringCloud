package com.example.demo_sse.config;

/**
 * *@ClassName config
 * *@Description TODO
 * *@Author 211295
 * *@Date 2025/9/24 15:51
 * *Version 1.0
 **/

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许跨域请求的路径
        registry.addMapping("/sse/**")
                .allowedOrigins("http://localhost:5173")  // 允许的来源
                .allowedMethods("GET", "POST")  // 允许的方法
                .allowedHeaders("*")  // 允许所有请求头
                .allowCredentials(true)  // 允许发送凭证（如cookies）
                .maxAge(3600);  // 预检请求缓存时间
    }
}
