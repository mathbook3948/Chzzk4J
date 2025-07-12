package com.github.mathbook3948.client.socket;

import io.socket.client.IO;
import io.socket.client.Socket;
import lombok.Getter;

import java.net.URISyntaxException;

public final class SocketClient {

    @Getter
    private final Socket socket;

    public SocketClient(String url, SocketEventHandler handler) {
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("sessionUrl must not be null or blank");
        }

        if (handler == null) {
            handler = new SocketEventHandler(null, null, null);
        }

        this.socket = init(url, handler);
    }

    private Socket init(String url, SocketEventHandler handler) {
        try {
            IO.Options options = new IO.Options();
            options.reconnection = false;
            options.forceNew = true;
            options.timeout = 3000;
            options.transports = new String[]{"websocket"};

            Socket s = IO.socket(url, options);
            s.on(Socket.EVENT_CONNECT, args -> handler.onConnect());
            s.on(Socket.EVENT_CONNECT_ERROR, args -> {
                Object error = args.length > 0 ? args[0] : null;
                handler.onConnectError(error);
            });
            s.on(Socket.EVENT_DISCONNECT, args -> handler.onDisconnect());
            return s;

        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid session URL: " + url, e);
        }
    }

    public void connect() {
        if (!socket.connected()) {
            socket.connect();
        }
    }

    public void disconnect() {
        socket.disconnect();
    }
}