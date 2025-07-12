package com.github.mathbook3948.client.socket;

import java.util.function.Consumer;

public class SocketEventHandler {

    private final Runnable onConnect;
    private final Consumer<Object> onConnectError;
    private final Runnable onDisconnect;

    public SocketEventHandler(Runnable onConnect, Consumer<Object> onConnectError, Runnable onDisconnect) {
        this.onConnect = onConnect != null ? onConnect : () -> {};
        this.onConnectError = onConnectError != null ? onConnectError : e -> {};
        this.onDisconnect = onDisconnect != null ? onDisconnect : () -> {};
    }

    public void onConnect() {
        onConnect.run();
    }

    public void onConnectError(Object error) {
        onConnectError.accept(error);
    }

    public void onDisconnect() {
        onDisconnect.run();
    }
}
