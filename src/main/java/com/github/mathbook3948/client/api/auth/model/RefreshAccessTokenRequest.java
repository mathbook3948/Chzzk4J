package com.github.mathbook3948.client.api.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Access Token 갱신 요청 모델.
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/authorization#access-token-1">공식 API 문서</a>를 참조하세요.</p>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RefreshAccessTokenRequest {

    /**
     * grantType은 "refresh_token"으로 고정
     */
    private final String grantType = "refresh_token";

    /**
     * 이전에 발급받은 Refresh Token
     */
    private String refreshToken;

    /**
     * 애플리케이션 등록 시 발급받은 Client ID.
     */
    private String clientId;

    /**
     * 애플리케이션 등록 시 발급받은 Client Secret.
     */
    private String clientSecret;
}
