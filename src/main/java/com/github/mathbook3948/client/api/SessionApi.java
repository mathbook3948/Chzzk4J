package com.github.mathbook3948.client.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mathbook3948.client.api.model.EmptyRequest;
import com.github.mathbook3948.client.api.model.session.*;
import com.github.mathbook3948.client.model.ApiRequest;
import com.github.mathbook3948.client.model.Response;
import com.github.mathbook3948.config.ChzzkClientConfig;
import org.eclipse.jetty.client.HttpClient;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>세션 생성, 세션 목록 조회, 이벤트 구독 및 취소를 할 수 있습니다.</p>
 * <p>세션 API 중 아래 API Scope를 호출하려면 사용자 계정으로 인증하여 얻은 Access Token이 필요합니다.</p>
 * <p>API Scope는 채팅 메시지 조회, 후원 조회입니다.</p>
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/session">공식 API 문서</a>를 참조하세요.</p>
 */
public class SessionApi extends AbstractApi {

    private final String clientId;

    private final String clientSecret;

    public SessionApi(HttpClient client, ObjectMapper objectMapper, String clientId, String clientSecret) {
        super(client, objectMapper);

        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    /**
     * <p>
     * Client 인증을 통해 소켓 연결을 위한 URL을 요청합니다. 생성된 URL은 일정 시간 동안만 유효합니다.
     * 최대 10개의 연결을 유지할 수 있습니다.
     * <p>자세한 내용은
     * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/live#undefined">공식 API 문서</a>를 참조하세요.</p>
     * </p>
     */
    public Response<CreateClientSessionResponse> createClientSession() {
        String path = "/open/v1/sessions/auth/client";

        Map<String, String> headers = ChzzkClientConfig.getClientHeaders(clientId, clientSecret);

        ApiRequest<EmptyRequest, CreateClientSessionResponse> request = new ApiRequest<>(path, null, headers, null, null, new TypeReference<>() {
        });

        return super.get(request);
    }

    /**
     * <p>
     * Access Token 인증을 통해 소켓 연결을 위한 URL을 요청합니다. 생성된 URL은 일정 시간 동안만 유효합니다.<br/>
     * 연결된 세션은 세션 생성에 사용된 Access Token과 동일한 유저 이벤트만 구독할 수 있습니다.<br/>
     * 유저별 최대 3개의 연결을 유지할 수 있습니다
     * <p>자세한 내용은
     * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/live#undefined-1">공식 API 문서</a>를 참조하세요.</p>
     * </p>
     * */
    public Response<?> createUserSession(String accessToken) {
        String path = "/open/v1/sessions/auth";

        ApiRequest<EmptyRequest, CreateUserSessionResponse> request = new ApiRequest<>(path, accessToken, null, null, null, new TypeReference<>() {
        });

        return super.get(request);
    }

    /**
     * <p>
     * Client 인증 기반의 생성된 세션을 조회합니다. 연결이 끊어진 세션은 일정 시간 동안만 조회가 가능합니다.
     * <p>자세한 내용은
     * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/live#undefined-3">공식 API 문서</a>를 참조하세요.</p>
     * </p>
     * */
    public Response<GetClientSessionListResponse> getClientSessionList(GetClientSessionListRequest req) {
        String path = "/open/v1/sessions/client";

        Map<String, String> headers = ChzzkClientConfig.getClientHeaders(clientId, clientSecret);
        Map<String, Object> queryParams = new HashMap<>();
        if (req.getSize() != null) queryParams.put("size", req.getSize().toString());
        if(req.getPage() != null) queryParams.put("page", req.getPage());

        ApiRequest<EmptyRequest, GetClientSessionListResponse> request = new ApiRequest<>(path, null, headers, queryParams, null, new TypeReference<>() {
        });

        return super.get(request);
    }

    /**
     * <p>
     * Client 인증 기반의 생성된 세션을 조회합니다. 연결이 끊어진 세션은 일정 시간 동안만 조회가 가능합니다.
     * <p>자세한 내용은
     * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/live#undefined-4">공식 API 문서</a>를 참조하세요.</p>
     * </p>
     * */
    public Response<GetUserSessionListResponse> getUserSessionList(GetUserSessionListRequest req) {
        String path = "/open/v1/sessions";

        Map<String, String> headers = ChzzkClientConfig.getClientHeaders(clientId, clientSecret);
        Map<String, Object> queryParams = new HashMap<>();
        if (req.getSize() != null) queryParams.put("size", req.getSize().toString());
        if(req.getPage() != null) queryParams.put("page", req.getPage());

        ApiRequest<EmptyRequest, GetUserSessionListResponse> request = new ApiRequest<>(path, null, headers, queryParams, null, new TypeReference<>() {
        });

        return super.get(request);
    }
}
