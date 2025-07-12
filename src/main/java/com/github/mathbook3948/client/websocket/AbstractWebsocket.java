package com.github.mathbook3948.client.websocket;

import com.github.mathbook3948.client.api.AbstractApi;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import java.net.URI;
import java.util.concurrent.TimeUnit;

public abstract class AbstractWebsocket {

    protected final WebSocketClient client;

    protected AbstractWebsocket(WebSocketClient client) {
        this.client = client;
    }

    protected void connect(String url, WebSocketAdapter adapter) {
        try {
            client.connect(adapter, URI.create(url)).get(5, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new RuntimeException("WebSocket connection failed: " + url, e);
        }
    }

    public abstract static class SimpleAdapter extends WebSocketAdapter {

        private Session session;

        @Override
        public void onWebSocketConnect(Session sess) {
            this.session = sess;
        }

        @Override
        public void onWebSocketClose(int statusCode, String reason) {
            this.session = null;
        }

        @Override
        public void onWebSocketError(Throwable cause) {
            // override if needed
        }

        @Override
        public void onWebSocketText(String message) {
            // override if needed
        }

        public void disconnect() {
            if (session != null && session.isOpen()) {
                session.close();
                session = null;
            }
        }

        public boolean isConnected() {
            return session != null && session.isOpen();
        }
    }
}
