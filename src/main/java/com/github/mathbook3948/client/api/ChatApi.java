package com.github.mathbook3948.client.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mathbook3948.client.api.model.EmptyRequest;
import com.github.mathbook3948.client.api.model.EmptyResponse;
import com.github.mathbook3948.client.api.model.chat.*;
import com.github.mathbook3948.client.model.ApiRequest;
import com.github.mathbook3948.client.model.Response;
import org.eclipse.jetty.client.HttpClient;

/**
 * <p>채팅 API로 채팅 전송, 채팅 공지 등록, 채팅 설정 조회, 채팅 설정 변경을 할 수 있습니다.</p>
 * <p>채팅 API를 호출하려면 사용자 계정으로 인증하여 얻은 Access Token이 필요합니다.</p>
 * <p>API Scope는 채팅 메시지 전송, 채팅 공지 등록, 채팅 설정 조회, 채팅 설정 변경입니다.</p>
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/live">공식 API 문서</a>를 참조하세요.</p>
 */
public class ChatApi extends AbstractApi {

    public ChatApi(HttpClient client, ObjectMapper objectMapper) {
        super(client, objectMapper);
    }

    /**
     * 채팅 메시지를 전송할 수 있습니다.
     * <p>자세한 내용은
     * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/chat#undefined">공식 API 문서</a>를 참조하세요.</p>
     */
    public Response<SendChatResponse> sendChat(String accessToken, SendChatRequest req) {
        String path = "/open/v1/chats/send";

        ApiRequest<SendChatRequest, SendChatResponse> request = new ApiRequest<>(path, accessToken, null, null, req, new TypeReference<>() {
        });

        return super.post(request);
    }

    /**
     * 채팅 공지사항을 등록할 수 있습니다.
     * 신규 메시지 또는 전송된 기존메시지로 공지사항을 등록이 가능합니다.
     * <p>자세한 내용은
     * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/chat#undefined-1">공식 API 문서</a>를 참조하세요.</p>
     */
    public Response<EmptyResponse> setChatNotice(String accessToken, RegisterNoticeRequest req) {
        String path = "/open/v1/chats/notice";

        ApiRequest<RegisterNoticeRequest, EmptyResponse> request = new ApiRequest<>(path, accessToken, null, null, req, new TypeReference<>() {
        });

        return super.post(request);
    }

    /**
     * 채널의 채팅 설정을 조회할 수 있습니다.
     * <p>자세한 내용은
     * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/chat#undefined-2">공식 API 문서</a>를 참조하세요.</p>
     */
    public Response<GetChatSettingsResponse> getChatSettings(String accessToken) {
        String path = "/open/v1/chats/settings";

        ApiRequest<EmptyRequest, GetChatSettingsResponse> request = new ApiRequest<>(path, accessToken, null, null, null, new TypeReference<>() {
        });

        return super.get(request);
    }

    /**
     * 채널의 채팅 설정을 변경할 수 있습니다.
     * <p>자세한 내용은
     * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/chat#undefined-3">공식 API 문서</a>를 참조하세요.</p>
     */
    public Response<EmptyResponse> updateChatSettings(String accessToken, UpdateChatSettingsRequest req) {
        String path = "/open/v1/chats/settings";

        ApiRequest<UpdateChatSettingsRequest, EmptyResponse> request = new ApiRequest<>(path, accessToken, null, null, req, new TypeReference<>() {
        });

        return super.put(request);
    }
}
