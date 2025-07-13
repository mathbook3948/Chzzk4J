package com.github.mathbook3948.config;

import java.util.Map;

public class ChzzkClientConfig {
    public static final String OPENAPI_BASE_URL = "https://openapi.chzzk.naver.com";

    public static final String API_BASE_URL = "https://api.chzzk.naver.com";

    public static final String API_BASE_URL_VERSION = "v3.1";

    public static final String OAUTH_BASE_URL = "https://chzzk.naver.com";

    public static Map<String, String> getClientHeaders(String clientId, String clientSecret) {
        return Map.of("Client-Id", clientId, "Client-Secret", clientSecret, "Content-Type", "application/json");
    }
}
