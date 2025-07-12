package com.github.mathbook3948;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mathbook3948.client.api.*;
import com.github.mathbook3948.client.api.auth.AuthorizationApi;
import com.github.mathbook3948.client.websocket.SessionWebsocket;
import lombok.Getter;
import lombok.experimental.Delegate;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.websocket.client.WebSocketClient;

@Getter
public class ChzzkClient {

    private final String clientId;
    private final String clientSecret;

    private final HttpClient httpClient;

    private final WebSocketClient webSocketClient;

    @Delegate
    private AuthorizationApi authorizationApi;

    @Delegate
    private ChatApi chatApi;

    @Delegate
    private LiveApi liveApi;

    @Delegate
    private CategoryApi categoryApi;

    @Delegate
    private ChannelApi channelApi;

    @Delegate
    private UserApi userApi;

    @Delegate
    private SessionApi sessionApi;

    @Delegate
    private SessionWebsocket sessionWebsocket;

    private ChzzkClient(HttpClient httpClient, WebSocketClient webSocketClient, ObjectMapper objectMapper, String clientId, String clientSecret) {
        if (clientId == null || clientSecret == null) {
            throw new IllegalArgumentException("clientId and clientSecret must not be null");
        }

        if (objectMapper == null) objectMapper = new ObjectMapper();
        if (httpClient == null) httpClient = new HttpClient();
        if (webSocketClient == null) webSocketClient = new WebSocketClient();

        this.clientId = clientId;
        this.clientSecret = clientSecret;

        try {
            this.httpClient = httpClient;
            this.httpClient.start();

            this.webSocketClient = webSocketClient;
            this.webSocketClient.start();

            this.chatApi = new ChatApi(this.httpClient, objectMapper);
            this.liveApi = new LiveApi(this.httpClient, objectMapper, clientId, clientSecret);
            this.categoryApi = new CategoryApi(this.httpClient, objectMapper, clientId, clientSecret);
            this.channelApi = new ChannelApi(this.httpClient, objectMapper, clientId, clientSecret);
            this.userApi = new UserApi(this.httpClient, objectMapper);
            this.sessionApi = new SessionApi(this.httpClient, objectMapper, clientId, clientSecret);
            this.sessionWebsocket = new SessionWebsocket(webSocketClient, clientId, clientSecret);
            this.authorizationApi = new AuthorizationApi(this.httpClient, objectMapper, clientId, clientSecret);
        } catch (Exception e) {
            throw new RuntimeException("Failed in ChzzkClient", e);
        }
    }

    public static ChzzkClient build(HttpClient httpClient, WebSocketClient webSocketClient, ObjectMapper objectMapper, String clientId, String clientSecret) {
        return new ChzzkClient(httpClient, webSocketClient, objectMapper, clientId, clientSecret);
    }

    public static ChzzkClient build(HttpClient httpClient, ObjectMapper objectMapper, String clientId, String clientSecret) {
        return new ChzzkClient(httpClient, null, objectMapper, clientId, clientSecret);
    }

    public static ChzzkClient build(String clientId, String clientSecret) {
        return new ChzzkClient(null, null, null, clientId, clientSecret);
    }

    public static ChzzkClient build(ObjectMapper objectMapper, String clientId, String clientSecret) {
        return new ChzzkClient(null, null, objectMapper, clientId, clientSecret);
    }
}

