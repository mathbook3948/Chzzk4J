package com.github.mathbook3948.client.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mathbook3948.client.api.model.EmptyRequest;
import com.github.mathbook3948.client.api.model.EmptyResponse;
import com.github.mathbook3948.client.api.model.live.*;
import com.github.mathbook3948.client.model.ApiRequest;
import com.github.mathbook3948.client.model.Response;
import com.github.mathbook3948.config.ChzzkClientConfig;
import org.eclipse.jetty.client.HttpClient;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>라이브 API로 전체 라이브 목록 조회, 방송 스트림키 조회, 방송 설정 조회, 방송 설정을 변경할 수 있습니다.</p>
 * <p>라이브 API 중 아래 API Scope를 호출하려면 사용자 계정으로 인증하여 얻은 Access Token이 필요합니다.</p>
 * <p>API Scope는 방송 스트림키 조회, 방송 설정 조회, 방송 설정 변경입니다.</p>
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/live">공식 API 문서</a>를 참조하세요.</p>
 */
public class LiveApi extends AbstractApi {

    private final String clientId;

    private final String clientSecret;

    public LiveApi(HttpClient client, ObjectMapper objectMapper, String clientId, String clientSecret) {
        super(client, objectMapper);

        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    /**
     * 현재 진행 중인 라이브의 전체 목록을 조회할 수 있습니다.
     * <p>자세한 내용은
     * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/live#undefined">공식 API 문서</a>를 참조하세요.</p>
     */
    public Response<GetLiveListResponse> getLiveList(GetLiveListRequest req) {
        String path = "/open/v1/lives";

        Map<String, String> headers = ChzzkClientConfig.getClientHeaders(clientId, clientSecret);
        Map<String, Object> queryParams = new HashMap<>();
        if (req.getSize() != null) queryParams.put("size", req.getSize().toString());
        if (req.getNext() != null) queryParams.put("next", req.getNext());

        ApiRequest<EmptyRequest, GetLiveListResponse> request = new ApiRequest<>(path, null, headers, queryParams, null, new TypeReference<>() {
        });

        return super.get(request);
    }

    /**
     * 스트리밍 채널의 스트림키를 조회할 수 있습니다.
     * <p>자세한 내용은
     * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/live#undefined-1">공식 API 문서</a>를 참조하세요.</p>
     */
    public Response<GetStreamKeyResponse> getStreamKey(String accessToken) {
        String path = "/open/v1/lives/streamkey";

        ApiRequest<EmptyRequest, GetStreamKeyResponse> request = new ApiRequest<>(path, accessToken, null, null, null, new TypeReference<>() {
        });

        return super.get(request);
    }

    /**
     * 스트리밍 채널의 방송 설정을 조회할 수 있습니다.
     * <p>자세한 내용은
     * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/live#undefined-2">공식 API 문서</a>를 참조하세요.</p>
     */
    public Response<GetLiveSettingsResponse> getLiveSettings(String accessToken) {
        String path = "/open/v1/lives/settings";

        ApiRequest<EmptyRequest, GetLiveSettingsResponse> request = new ApiRequest<>(path, accessToken, null, null, null, new TypeReference<>() {
        });

        return super.get(request);
    }

    /**
     * 스트리밍 채널의 방송 설정을 변경할 수 있습니다.
     * 설정 변경 시 API 요청을 통해 필요한 특정 값만 변경하는 것도 가능합니다.
     * <p>자세한 내용은
     * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/live#undefined-3">공식 API 문서</a>를 참조하세요.</p>
     */
    public Response<?> updateLiveSettings(String accessToken, UpdateLiveSettingsRequest req) {
        String path = "/open/v1/lives/settings";

        ApiRequest<UpdateLiveSettingsRequest, EmptyResponse> request = new ApiRequest<>(path, accessToken, null, null, req, new TypeReference<>() {
        });

        return super.patch(request);
    }
}
