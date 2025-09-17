package org.example.mybokesys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * *@ClassName WebSocketConfig
 * *@Description TODO
 * *@Author 211295
 * *@Date 2025/9/16 7:57
 * *Version 1.0
 **/
@Configuration
// 扫描配置类 扫描@ServerEndpoint("/ws-article")注解的类，并且注入到springboot中
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}