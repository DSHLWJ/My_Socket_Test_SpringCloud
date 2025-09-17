package org.example.mybokesys.webSocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * *@ClassName ArticleWebSocket
 * *@Description TODO
 * *@Author 211295
 * *@Date 2025/9/16 7:24
 * *Version 1.0
 **/
@ServerEndpoint("/ws-article")
@Component
public class ArticleWebSocket {
    // 连接打开时触发
    @OnOpen
    public void onOpen(Session session) {
        // 只处理来自代理服务的连接
        System.out.println("代理服务已连接，用户消息可推送");

        // 可以在这里进行身份验证，或者验证来源等
        // 例如：session.getRequestParameterMap().get("token")
    }

    // 接收到客户端消息时触发
    @OnMessage
    public void onMessage(String message, Session session) {
        // 业务处理
        System.out.println("收到消息: " + message);
        // 给当前客户端发送回复
        try {
            session.getBasicRemote().sendText("服务器收到44567899999: " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 连接关闭时触发
    @OnClose
    public void onClose(Session session) {
        System.out.println("代理服务已断开连接，清理资源等");
    }

    // 发生错误时触发
    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("WebSocket 错误: " + throwable.getMessage());
        throwable.printStackTrace();
    }


}
