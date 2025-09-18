package org.example.mybokesys.webSocket;

import com.ruoyi.common.core.constant.HttpStatus;
import com.ruoyi.common.core.exception.auth.NotLoginException;
import com.ruoyi.common.core.utils.JwtUtils;
import com.ruoyi.common.core.utils.ServletUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.model.LoginUser;
import io.jsonwebtoken.Claims;
import org.example.mybokesys.config.MyProxyConfigurator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.URI;

import static com.ruoyi.common.security.auth.AuthUtil.getLoginUser;

@ServerEndpoint(value = "/ws-proxy/{key}", configurator = MyProxyConfigurator.class)
@Component
public class ProxyWebSocket {
    private Session clientSession;
    private Session backendSession;

    @OnOpen
    public void onOpen(Session session, @PathParam("key") String key) throws IOException, DeploymentException {


        // 获取 WebSocket 连接时路径中的 key 参数
        System.out.println("WebSocket 连接成功，提取的 key: " + key);

        LoginUser loginUser =  getLoginUser(key);

        if (loginUser == null)
        {
            throw new NotLoginException("无效的token");
        }

        this.clientSession = session;
        // 连接后端 WebSocket
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        // 使用独立类
        backendSession = container.connectToServer(new BackendClientEndpoint(clientSession), URI.create("ws://127.0.0.1:9623/ws-article"));


    }




    @OnMessage
    public void onMessage(String message) throws IOException {
        System.out.println(message);
        if (backendSession != null) backendSession.getBasicRemote().sendText(message);
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) throws IOException {
        if (backendSession != null && backendSession.isOpen()) backendSession.close();
    }


    @OnError
    public void onError(Session session, Throwable throwable) {
        System.err.println("WebSocket Error, sessionId: " + (session != null ? session.getId() : "null"));
        throwable.printStackTrace();
    }
}
