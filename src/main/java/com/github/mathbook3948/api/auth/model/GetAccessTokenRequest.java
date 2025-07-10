package com.github.mathbook3948.api.auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Access Token 발급 요청 모델.
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/authorization#access-token">공식 API 문서</a>를 참조하세요.</p>
 */
@Getter
@AllArgsConstructor
public class GetAccessTokenRequest {

    /**
     * grantType은 "refresh_token"으로 고정
     */
    private String grantType;

    /**
     * 애플리케이션 등록 시 발급받은 Client ID.
     */
    private String clientId;

    /**
     * 애플리케이션 등록 시 발급받은 Client Secret.
     */
    private String clientSecret;

    /**
     * 인증 코드 요청 시 전달받은 code.
     */
    private String code;

    /**
     * 인증 코드 요청 시 전달한 state 값.
     */
    private String state;
}
