package org.example.mybokesys.webSocket;

/**
 * *@ClassName ProxyConfigurator
 * *@Description TODO
 * *@Author 211295
 * *@Date 2025/9/16 7:26
 * *Version 1.0
 **/
import org.springframework.context.annotation.Configuration;

import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import java.util.Map;

@Configuration
public class ProxyConfigurator extends ServerEndpointConfig.Configurator {

    @Override
    public void modifyHandshake(ServerEndpointConfig config,
                                HandshakeRequest request,
                                HandshakeResponse response) {
        // 可以把 Header 或其他信息放到 userProperties
        Map<String, java.util.List<String>> headers = request.getHeaders();
        config.getUserProperties().put("headers", headers);
    }
}
