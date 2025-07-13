package com.github.mathbook3948.client.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mathbook3948.client.model.ApiRequest;
import com.github.mathbook3948.client.model.Response;
import com.github.mathbook3948.config.ChzzkClientConfig;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.ByteBufferRequestContent;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.http.MimeTypes;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Map;

public abstract class AbstractApi {

    private final String baseUrl = ChzzkClientConfig.OPENAPI_BASE_URL;

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
                boolean first = true;
                for (Map.Entry<String, Object> entry : req.getQueryParams().entrySet()) {
                    String key = URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8);

                    Object value = entry.getValue();
                    if (value instanceof String[]) {
                        for (String v : (String[]) value) {
                            appendParam(urlBuilder, key, v, first);
                            first = false;
                        }
                    } else if (value instanceof Collection) {
                        for (Object v : (Collection<?>) value) {
                            appendParam(urlBuilder, key, String.valueOf(v), first);
                            first = false;
                        }
                    } else {
                        appendParam(urlBuilder, key, String.valueOf(value), first);
                        first = false;
                    }
                }
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
                for (Map.Entry<String, Object> entry : req.getQueryParams().entrySet()) {
                    String key = URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8);

                    Object value = entry.getValue();
                    if (value instanceof String[]) {
                        for (String v : (String[]) value) {
                            appendParam(urlBuilder, key, v, first);
                            first = false;
                        }
                    } else if (value instanceof Collection) {
                        for (Object v : (Collection<?>) value) {
                            appendParam(urlBuilder, key, String.valueOf(v), first);
                            first = false;
                        }
                    } else {
                        appendParam(urlBuilder, key, String.valueOf(value), first);
                        first = false;
                    }
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
                for (Map.Entry<String, Object> entry : req.getQueryParams().entrySet()) {
                    String key = URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8);

                    Object value = entry.getValue();
                    if (value instanceof String[]) {
                        for (String v : (String[]) value) {
                            appendParam(urlBuilder, key, v, first);
                            first = false;
                        }
                    } else if (value instanceof Collection) {
                        for (Object v : (Collection<?>) value) {
                            appendParam(urlBuilder, key, String.valueOf(v), first);
                            first = false;
                        }
                    } else {
                        appendParam(urlBuilder, key, String.valueOf(value), first);
                        first = false;
                    }
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
                for (Map.Entry<String, Object> entry : req.getQueryParams().entrySet()) {
                    String key = URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8);

                    Object value = entry.getValue();
                    if (value instanceof String[]) {
                        for (String v : (String[]) value) {
                            appendParam(urlBuilder, key, v, first);
                            first = false;
                        }
                    } else if (value instanceof Collection) {
                        for (Object v : (Collection<?>) value) {
                            appendParam(urlBuilder, key, String.valueOf(v), first);
                            first = false;
                        }
                    } else {
                        appendParam(urlBuilder, key, String.valueOf(value), first);
                        first = false;
                    }
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

    private void appendParam(StringBuilder builder, String key, String value, boolean isFirst) {
        if (!isFirst) {
            builder.append("&");
        }
        builder.append(key);
        builder.append("=");
        builder.append(URLEncoder.encode(value, StandardCharsets.UTF_8));
    }
}
