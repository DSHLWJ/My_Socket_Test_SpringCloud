package com.example.demo_sse.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * *@ClassName SseService
 * *@Description TODO
 * *@Author 211295
 * *@Date 2025/9/24 16:17
 * *Version 1.0
 **/
public interface SseService {


    /**
     * 获取连接
     * @param clientId 客户端id
     * @return
     */
    SseEmitter getConn(String clientId);

    /**
     *  发送消息到指定客户端
     * @param clientId 客户端id
     * @throws Exception
     */
    void send(String clientId);

    /**
     * 发送消息到所有SSE客户端
     * @throws Exception
     */
    void sendToAll() throws Exception;

    /**
     * 关闭指定客户端的连接
     * @param clientId 客户端id
     */
    void closeConn(String clientId);
}

