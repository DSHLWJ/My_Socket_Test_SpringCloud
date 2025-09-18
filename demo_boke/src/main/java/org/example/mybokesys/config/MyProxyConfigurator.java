package org.example.mybokesys.config;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import java.util.List;

/**
 * *@ClassName MyProxyConfigurator
 * *@Description TODO
 * *@Author 211295
 * *@Date 2025/9/16 20:47
 * *Version 1.0
 **/

/**
 * 继承自ServerEndpointConfig.Configurator
 * 重写 modifyHandshake 方法获取request 里面的Cookie
 * 不推荐使用这种方法，会把用户秘密也跟着拿到
 * 最好还是根据token拿取
 * webSocket是靠session会话连接的
 * **/
public class MyProxyConfigurator extends ServerEndpointConfig.Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
        // 获取 HTTP 请求头
        List<String> cookies = request.getHeaders().get("Cookie");

        if (cookies != null && !cookies.isEmpty()) {
            // 如果存在 Cookie，打印或处理
            String cookieHeader = cookies.get(0);
            //System.out.println("收到的 Cookie: " + cookieHeader);
            // 你可以将 Cookie 存储到 WebSocket 会话中，以便后续使用
            config.getUserProperties().put("cookies", cookieHeader);
        }

        super.modifyHandshake(config, request, response);
    }
}