package com.github.mathbook3948.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mathbook3948.api.model.EmptyRequest;
import com.github.mathbook3948.api.model.user.GetUserInfoResponse;
import com.github.mathbook3948.model.ApiRequest;
import com.github.mathbook3948.model.Response;
import org.eclipse.jetty.client.HttpClient;

/**
 * <p>카테고리 검색 API로 카테고리 목록 및 정보를 조회할 수 있습니다.</p>
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/category">공식 API 문서</a>를 참조하세요.</p>
 */
public class UserApi extends AbstractApi {

    public UserApi(HttpClient client, ObjectMapper objectMapper) {
        super(client, objectMapper);
    }

    public Response<GetUserInfoResponse> getUserInfo(String accessToken) {
        String path = "/open/v1/users/me";

        ApiRequest<EmptyRequest, GetUserInfoResponse> request = new ApiRequest<>(path, accessToken, null, null, null, new TypeReference<>() {
        });

        return super.get(request);
    }
}
