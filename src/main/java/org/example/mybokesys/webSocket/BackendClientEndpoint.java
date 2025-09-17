package org.example.mybokesys.webSocket;

/**
 * *@ClassName BackendClientEndpoint
 * *@Description TODO
 * *@Author 211295
 * *@Date 2025/9/16 9:21
 * *Version 1.0
 **/
import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import java.io.IOException;

@ClientEndpoint
public class BackendClientEndpoint {

    private Session proxySession;

    public BackendClientEndpoint(Session proxySession) {
        this.proxySession = proxySession;
    }

    @OnMessage
    public void onMessage(String message) {
        try {
            proxySession.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
