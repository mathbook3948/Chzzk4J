package com.github.mathbook3948.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mathbook3948.api.model.*;
import com.github.mathbook3948.model.ApiRequest;
import com.github.mathbook3948.model.Response;
import org.eclipse.jetty.client.HttpClient;

public class ChatApi extends AbstractApi {

    public ChatApi(HttpClient client, ObjectMapper objectMapper, String baseUrl) {
        super(client, objectMapper, baseUrl);
    }

    public Response<SendChatResponse> sendChat(String accessToken, SendChatRequest req) {
        String path = "/open/v1/chats/send";

        ApiRequest<SendChatRequest, SendChatResponse> request = new ApiRequest<>(path, accessToken, null, null, req, new TypeReference<>() {
        });

        return super.post(request);
    }

    public Response<EmptyResponse> setChatNotice(String accessToken, RegisterNoticeRequest req) {
        String path = "/open/v1/chats/notice";

        ApiRequest<RegisterNoticeRequest, EmptyResponse> request = new ApiRequest<>(path, accessToken, null, null, req, new TypeReference<>() {
        });

        return super.post(request);
    }

    public Response<GetChatSettingsResponse> getChatSettings(String accessToken) {
        String path = "/open/v1/chats/settings";

        ApiRequest<EmptyRequest, GetChatSettingsResponse> request = new ApiRequest<>(path, accessToken, null, null, null, new TypeReference<>() {
        });

        return super.get(request);
    }
}
