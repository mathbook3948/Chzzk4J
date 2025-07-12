package com.github.mathbook3948.client.model;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class ApiRequest<Req, Res> {
    private final String path;
    private final String accessToken;
    private final Map<String, String> headers;
    private final Map<String, String> queryParams;
    private final Req body;
    private final TypeReference<Response<Res>> responseType;
}
