package com.github.mathbook3948.api.auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Access Token 갱신 응답 모델.
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/authorization#access-token-1">공식 API 문서</a>를 참조하세요.</p>
 */
@Getter
@AllArgsConstructor
public class RefreshAccessTokenResponse {

    /**
     * 발급된 Access Token.
     */
    private String accessToken;

    /**
     * 발급된 Refresh Token.
     */
    private String refreshToken;

    /**
     * 토큰 타입. {@code Bearer} 고정.
     */
    private String tokenType;

    /**
     * Access Token 만료 시간(초 단위). 예: {@code 86400}.
     */
    private String expiresIn;

    /**
     * 채널 조회
     */
    private String scope;
}
