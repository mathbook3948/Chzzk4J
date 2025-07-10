package com.github.mathbook3948.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mathbook3948.config.ChzzkClientConfig;
import com.github.mathbook3948.model.ApiRequest;
import com.github.mathbook3948.model.Response;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.ByteBufferRequestContent;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.http.MimeTypes;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public abstract class AbstractApi {

    private final String baseUrl = ChzzkClientConfig.API_BASE_URL;

    private final HttpClient client;

    protected final ObjectMapper objectMapper;

    protected AbstractApi(HttpClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    protected <Req, Res> Response<Res> get(ApiRequest<Req, Res> req) {
        try {
            StringBuilder urlBuilder = new StringBuilder(baseUrl).append(req.getPath());

            // 쿼리 파라미터 처리
            if (req.getQueryParams() != null && !req.getQueryParams().isEmpty()) {
                urlBuilder.append("?");
                req.getQueryParams().forEach((k, v) ->
                        urlBuilder.append(URLEncoder.encode(k, StandardCharsets.UTF_8))
                                .append("=")
                                .append(URLEncoder.encode(v, StandardCharsets.UTF_8))
                );
                // 마지막 & 제거
                urlBuilder.setLength(urlBuilder.length() - 1);
            }

            Request jettyRequest = client.newRequest(urlBuilder.toString())
                    .method(HttpMethod.GET);

            if (req.getAccessToken() != null || req.getHeaders() != null) {
                jettyRequest.headers(fields -> {
                    if (req.getAccessToken() != null) {
                        fields.add(HttpHeader.AUTHORIZATION, "Bearer " + req.getAccessToken());
                    }
                    if (req.getHeaders() != null) {
                        req.getHeaders().forEach(fields::add);
                    }
                });
            }

            ContentResponse res = jettyRequest.send();
            String body = res.getContentAsString();

            return objectMapper.readValue(body, req.getResponseType());
        } catch (Exception e) {
            throw new RuntimeException("GET request failed: " + req.getPath(), e);
        }
    }

    protected <Req, Res> Response<Res> post(ApiRequest<Req, Res> req) {
        try {
            StringBuilder urlBuilder = new StringBuilder(baseUrl).append(req.getPath());

            if (req.getQueryParams() != null && !req.getQueryParams().isEmpty()) {
                urlBuilder.append("?");
                boolean first = true;
                for (var entry : req.getQueryParams().entrySet()) {
                    if (!first) urlBuilder.append("&");
                    urlBuilder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8))
                            .append("=")
                            .append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
                    first = false;
                }
            }

            String jsonBody = objectMapper.writeValueAsString(req.getBody());
            ByteBufferRequestContent body = new ByteBufferRequestContent(
                    MimeTypes.Type.APPLICATION_JSON.asString(),
                    StandardCharsets.UTF_8.encode(jsonBody)
            );

            Request jettyRequest = client.newRequest(urlBuilder.toString())
                    .method(HttpMethod.POST)
                    .body(body);

            jettyRequest.headers(fields -> {
                if (req.getAccessToken() != null) {
                    fields.add(HttpHeader.AUTHORIZATION, "Bearer " + req.getAccessToken());
                }
                if (req.getHeaders() != null) {
                    req.getHeaders().forEach(fields::add);
                }
            });


            ContentResponse res = jettyRequest.send();
            String bodyStr = res.getContentAsString();

            return objectMapper.readValue(bodyStr, req.getResponseType());
        } catch (Exception e) {
            throw new RuntimeException("POST request failed: " + req.getPath(), e);
        }
    }

    protected <Req, Res> Response<Res> put(ApiRequest<Req, Res> req) {
        try {
            StringBuilder urlBuilder = new StringBuilder(baseUrl).append(req.getPath());

            if (req.getQueryParams() != null && !req.getQueryParams().isEmpty()) {
                urlBuilder.append("?");
                boolean first = true;
                for (var entry : req.getQueryParams().entrySet()) {
                    if (!first) urlBuilder.append("&");
                    urlBuilder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8))
                            .append("=")
                            .append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
                    first = false;
                }
            }

            String jsonBody = objectMapper.writeValueAsString(req.getBody());
            ByteBufferRequestContent body = new ByteBufferRequestContent(
                    MimeTypes.Type.APPLICATION_JSON.asString(),
                    StandardCharsets.UTF_8.encode(jsonBody)
            );

            Request jettyRequest = client.newRequest(urlBuilder.toString())
                    .method(HttpMethod.PUT)
                    .body(body);

            jettyRequest.headers(fields -> {
                if (req.getAccessToken() != null) {
                    fields.add(HttpHeader.AUTHORIZATION, "Bearer " + req.getAccessToken());
                }
                if (req.getHeaders() != null) {
                    req.getHeaders().forEach(fields::add);
                }
            });

            ContentResponse res = jettyRequest.send();
            String bodyStr = res.getContentAsString();

            return objectMapper.readValue(bodyStr, req.getResponseType());
        } catch (Exception e) {
            throw new RuntimeException("PUT request failed: " + req.getPath(), e);
        }
    }

    protected <Req, Res> Response<Res> patch(ApiRequest<Req, Res> req) {
        try {
            StringBuilder urlBuilder = new StringBuilder(baseUrl).append(req.getPath());

            if (req.getQueryParams() != null && !req.getQueryParams().isEmpty()) {
                urlBuilder.append("?");
                boolean first = true;
                for (var entry : req.getQueryParams().entrySet()) {
                    if (!first) urlBuilder.append("&");
                    urlBuilder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8))
                            .append("=")
                            .append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
                    first = false;
                }
            }

            String jsonBody = objectMapper.writeValueAsString(req.getBody());
            ByteBufferRequestContent body = new ByteBufferRequestContent(
                    MimeTypes.Type.APPLICATION_JSON.asString(),
                    StandardCharsets.UTF_8.encode(jsonBody)
            );

            Request jettyRequest = client.newRequest(urlBuilder.toString())
                    .method(HttpMethod.PATCH)
                    .body(body);

            jettyRequest.headers(fields -> {
                if (req.getAccessToken() != null) {
                    fields.add(HttpHeader.AUTHORIZATION, "Bearer " + req.getAccessToken());
                }
                if (req.getHeaders() != null) {
                    req.getHeaders().forEach(fields::add);
                }
            });

            ContentResponse res = jettyRequest.send();
            String bodyStr = res.getContentAsString();

            return objectMapper.readValue(bodyStr, req.getResponseType());
        } catch (Exception e) {
            throw new RuntimeException("PATCH request failed: " + req.getPath(), e);
        }
    }
}
