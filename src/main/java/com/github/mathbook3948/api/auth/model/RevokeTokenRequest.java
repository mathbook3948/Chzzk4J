package com.github.mathbook3948.api.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Access Token 삭제 요청 모델.
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/authorization#access-token-2">공식 API 문서</a>를 참조하세요.</p>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RevokeTokenRequest {

    /**
     * 애플리케이션 등록 시 발급받은 Client ID.
     */
    private String clientId;

    /**
     * 애플리케이션 등록 시 발급받은 Client Secret.
     */
    private String clientSecret;

    /**
     * 삭제를 원하는 Access/Refresh token
     */
    private String token;

    /**
     * 토큰 종류 힌트
     * <p>
     * 기본값은 {@code access_token}이며, {@code refresh_token}도 가능.
     * 치지직 서버가 어떤 종류의 토큰을 해제해야 하는지 명확히 지정.
     * </p>
     */
    private String tokenTypeHint;
}
