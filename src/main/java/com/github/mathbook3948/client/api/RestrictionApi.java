package com.github.mathbook3948.client.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mathbook3948.client.api.model.EmptyRequest;
import com.github.mathbook3948.client.api.model.EmptyResponse;
import com.github.mathbook3948.client.api.model.live.GetLiveListRequest;
import com.github.mathbook3948.client.api.model.live.GetLiveListResponse;
import com.github.mathbook3948.client.api.model.restriction.GetRestrictChannelsResponse;
import com.github.mathbook3948.client.api.model.restriction.RestrictChannelRequest;
import com.github.mathbook3948.client.model.ApiRequest;
import com.github.mathbook3948.client.model.Response;
import com.github.mathbook3948.config.ChzzkClientConfig;
import org.eclipse.jetty.client.HttpClient;

import java.util.HashMap;
import java.util.Map;

/**
 * 활동 제한 API로 활동 제한 추가, 삭제, 목록 조회를 할 수 있습니다.<br/>
 * 활동 제한을 호출하려면 사용자 계정으로 인증하여 얻은 Access Token이 필요합니다.<br/>
 * API Scope는 활동 제한 추가, 활동 제한 삭제, 활동 제한 목록 조회입니다.
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/restriction">공식 API 문서</a>를 참조하세요.</p>
 */
public class RestrictionApi extends AbstractApi {

    private final String clientId;

    private final String clientSecret;

    public RestrictionApi(HttpClient client, ObjectMapper objectMapper, String clientId, String clientSecret) {
        super(client, objectMapper);

        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    /**
     * 사용자를 활동 제한 대상으로 추가할 수 있습니다.
     * <p>자세한 내용은
     * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/restriction#undefined">공식 API 문서</a>를 참조하세요.</p>
     */
    public Response<EmptyResponse> restrictChannel(String accessToken, RestrictChannelRequest req) {
        String path = "/open/v1/restrict-channels";

        ApiRequest<RestrictChannelRequest, EmptyResponse> request = new ApiRequest<>(path, accessToken, null, null, req, new TypeReference<>() {
        });

        return super.post(request);
    }

    /**
     * 사용자를 활동 제한 대상에서 삭제할 수 있습니다.
     * <p>자세한 내용은
     * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/restriction#undefined-1">공식 API 문서</a>를 참조하세요.</p>
     */
    public Response<EmptyResponse> deleteRestrictChannel(String accessToken, RestrictChannelRequest req) {
        String path = "/open/v1/restrict-channels";

        ApiRequest<RestrictChannelRequest, EmptyResponse> request = new ApiRequest<>(path, accessToken, null, null, req, new TypeReference<>() {
        });

        return super.delete(request);
    }

    /**
     * 채널의 활동 제한 목록을 조회할 수 있습니다.
     * <p>자세한 내용은
     * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/restriction#undefined-2">공식 API 문서</a>를 참조하세요.</p>
     */
    public Response<GetRestrictChannelsResponse> getRestrictChannels(String accessToken, int size, String next) {
        String path = "/open/v1/restrict-channels";

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("size", size);
        queryParams.put("next", next);

        ApiRequest<EmptyRequest, GetRestrictChannelsResponse> request = new ApiRequest<>(path, accessToken, null, queryParams, null, new TypeReference<>() {
        });

        return super.get(request);
    }

}
