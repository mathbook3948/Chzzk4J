package com.github.mathbook3948.api.auth;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mathbook3948.api.AbstractApi;
import com.github.mathbook3948.api.auth.model.*;
import com.github.mathbook3948.api.model.EmptyResponse;
import com.github.mathbook3948.model.ApiRequest;
import com.github.mathbook3948.model.Response;
import org.eclipse.jetty.client.HttpClient;

/**
 * <p>치지직 Open API 사용과 인증에 관련된 클래스입니다.</p>
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/authorization">공식 API 문서</a>를 참조하세요.</p>
 */
public class AuthorizationApi extends AbstractApi {

    private final String clientId;

    private final String clientSecret;

    public AuthorizationApi(HttpClient client, ObjectMapper objectMapper, String clientId, String clientSecret) {
        super(client, objectMapper);

        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    /**
     * <p>
     * Open API 사용 중, 유저 인증을 위한 토큰을 발급 받습니다.
     * Access Token 의 만료기간은 1일, Refresh Token 의 만료기간은 30일 입니다.
     * <p>자세한 내용은
     * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/authorization#access-token">공식 API 문서</a>를 참조하세요.</p>
     * </p>
     */
    public Response<GetAccessTokenResponse> getAccessToken(GetAccessTokenRequest req) {
        String path = "/auth/v1/token";

        ApiRequest<GetAccessTokenRequest, GetAccessTokenResponse> request = new ApiRequest<>(path, null, null, null, req, new TypeReference<>() {
        });

        return super.post(request);
    }

    /**
     * <p>
     * <p>
     * Access Token은 만료 주기를 갖습니다. 해당 만료 주기가 지나면 해당 Access Token을 사용한 API 호출은 401(INVALID_TOKEN) 응답을 반환합니다.
     * Access Token이 만료되면, Refresh Token을 통하여 Access Token을 재발급 받아 사용해야 합니다.
     * </p>
     * <p>
     * Refresh Token은 Access Token 보다 긴 만료기간을 가지며, 일회용으로 사용됩니다.
     * Refresh Token 또한 만료되면 Access Token 발급 과정을 통해 새로운 Access Token을 발급받아야 합니다.
     * </p>
     * <p>자세한 내용은
     * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/authorization#access-token-1">공식 API 문서</a>를 참조하세요.</p>
     * </p>
     * */
    public Response<RefreshAccessTokenResponse> refreshAccessToken(String refreshToken) {
        String path = "/auth/v1/token";

        RefreshAccessTokenRequest req = new RefreshAccessTokenRequest(refreshToken, clientId, clientSecret);

        ApiRequest<RefreshAccessTokenRequest, RefreshAccessTokenResponse> request = new ApiRequest<>(path, null, null, null, req, new TypeReference<>() {
        });

        return super.post(request);
    }

    /**
     * <p>
     * 유저가 로그아웃하는 등, 해당 Access Token, Refresh Token 의 revoke 가 필요할 경우 호출합니다.
     * 요청한 Token 과 동일한 인증 과정을 거친 모든 Token 이 제거됩니다. (clientId 와 user 가 동일한 Token)
     * <p>자세한 내용은
     * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/authorization#access-token-2">공식 API 문서</a>를 참조하세요.</p>
     * </p>
     */
    public Response<EmptyResponse> revokeToken(String token, String tokenTypeHint) {
        String path = "/auth/v1/token/revoke";

        RevokeTokenRequest req = new RevokeTokenRequest(clientId, clientSecret, token, tokenTypeHint);

        ApiRequest<RevokeTokenRequest, EmptyResponse> request = new ApiRequest<>(path, null, null, null, req, new TypeReference<>() {
        });

        return super.post(request);
    }
}
