package com.github.mathbook3948.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mathbook3948.api.model.*;
import com.github.mathbook3948.model.ApiRequest;
import com.github.mathbook3948.model.Response;
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
     * <p>
     * 현재 진행 중인 라이브의 전체 목록을 조회할 수 있습니다.
     * <p>자세한 내용은
     * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/live#undefined">공식 API 문서</a>를 참조하세요.</p>
     * </p>
     */
    public Response<GetLiveListResponse> getLiveList(GetLiveListRequest req) {
        String path = "/open/v1/lives";

        Map<String, String> headers = Map.of("Client-Id", clientId, "Client-Secret", clientSecret, "Content-Type", "application/json");
        Map<String, String> queryParams = new HashMap<>();
        if(req.getSize() != null) queryParams.put("size", req.getSize().toString());
        if(req.getNext() != null) queryParams.put("next", req.getNext());

        ApiRequest<EmptyRequest, GetLiveListResponse> request = new ApiRequest<>(path, null, headers, queryParams, null, new TypeReference<>() {
        });

        return super.get(request);
    }

    /**
     * <p>
     * 스트리밍 채널의 스트림키를 조회할 수 있습니다.
     * <p>자세한 내용은
     * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/live#undefined-1">공식 API 문서</a>를 참조하세요.</p>
     * </p>
     */
    public Response<?> getStreamKey() {
        String path = "/open/v1/lives/streamkey";
    }
}
