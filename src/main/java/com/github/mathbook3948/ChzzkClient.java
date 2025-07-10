package com.github.mathbook3948;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mathbook3948.api.*;
import com.github.mathbook3948.api.auth.AuthorizationApi;
import lombok.Getter;
import lombok.experimental.Delegate;
import org.eclipse.jetty.client.HttpClient;

@Getter
public class ChzzkClient {

    private final String clientId;
    private final String clientSecret;

    private final HttpClient client;

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

    private ChzzkClient(ObjectMapper objectMapper, String clientId, String clientSecret) {
        if (clientId == null || clientSecret == null) {
            throw new IllegalArgumentException("clientId and clientSecret must not be null");
        }

        this.clientId = clientId;
        this.clientSecret = clientSecret;

        try {
            this.client = new HttpClient();
            this.client.start();

            this.chatApi = new ChatApi(this.client, objectMapper);
            this.liveApi = new LiveApi(this.client, objectMapper, clientId, clientSecret);
            this.categoryApi = new CategoryApi(this.client, objectMapper, clientId, clientSecret);
            this.channelApi = new ChannelApi(this.client, objectMapper, clientId, clientSecret);
            this.authorizationApi = new AuthorizationApi(this.client, objectMapper, clientId, clientSecret);
            this.userApi = new UserApi(this.client, objectMapper);
        } catch (Exception e) {
            throw new RuntimeException("Failed to start HttpClient", e);
        }
    }

    public static ChzzkClient build(ObjectMapper objectMapper, String clientId, String clientSecret) {
        return new ChzzkClient(
                objectMapper,
                clientId,
                clientSecret
        );
    }
}

