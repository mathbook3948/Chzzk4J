package com.github.mathbook3948.client.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mathbook3948.client.api.model.EmptyRequest;
import com.github.mathbook3948.client.api.model.category.SearchCategoriesRequest;
import com.github.mathbook3948.client.api.model.category.SearchCategoryResponse;
import com.github.mathbook3948.client.model.ApiRequest;
import com.github.mathbook3948.client.model.Response;
import com.github.mathbook3948.config.ChzzkClientConfig;
import org.eclipse.jetty.client.HttpClient;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>카테고리 검색 API로 카테고리 목록 및 정보를 조회할 수 있습니다.</p>
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/category">공식 API 문서</a>를 참조하세요.</p>
 */
public class CategoryApi extends AbstractApi {

    private final String clientId;

    private final String clientSecret;

    public CategoryApi(HttpClient client, ObjectMapper objectMapper, String clientId, String clientSecret) {
        super(client, objectMapper);

        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    /**
     * <p>
     * 카테고리를 검색하여 목록 및 정보를 조회할 수 있습니다.
     * <p>자세한 내용은
     * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/category#undefined">공식 API 문서</a>를 참조하세요.</p>
     * </p>
     */
    public Response<SearchCategoryResponse> searchCategories(SearchCategoriesRequest req) {
        String path = "/open/v1/categories/search";
        Map<String, String> headers = ChzzkClientConfig.getClientHeaders(clientId, clientSecret);
        Map<String, Object> queryParams = new HashMap<>();
        if (req.getSize() != null) queryParams.put("size", req.getSize().toString());
        queryParams.put("query", req.getQuery());

        ApiRequest<EmptyRequest, SearchCategoryResponse> request = new ApiRequest<>(path, null, headers, queryParams, null, new TypeReference<>() {
        });

        return super.get(request);
    }
}
