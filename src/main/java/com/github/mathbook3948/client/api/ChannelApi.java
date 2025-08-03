package com.github.mathbook3948.client.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mathbook3948.client.api.model.EmptyRequest;
import com.github.mathbook3948.client.api.model.channel.*;
import com.github.mathbook3948.client.model.ApiRequest;
import com.github.mathbook3948.client.model.Response;
import com.github.mathbook3948.config.ChzzkClientConfig;
import org.eclipse.jetty.client.HttpClient;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>채널 정보 조회 API로 채널 정보를 조회할 수 있습니다. </p>
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/channel">공식 API 문서</a>를 참조하세요.</p>
 */
public class ChannelApi extends AbstractApi {

    private final String clientId;

    private final String clientSecret;

    public ChannelApi(HttpClient client, ObjectMapper objectMapper, String clientId, String clientSecret) {
        super(client, objectMapper);

        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    /**
     * 채널 정보를 조회할 수 있습니다.
     * <p>자세한 내용은
     * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/channel#undefined">공식 API 문서</a>를 참조하세요.</p>
     */
    public Response<GetChannelInfoResponse> getChannelInfo(GetChannelInfoRequest req) {
        String path = "/open/v1/channels";
        Map<String, String> headers = ChzzkClientConfig.getClientHeaders(clientId, clientSecret);
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("channelIds", req.getChannelIds());

        ApiRequest<EmptyRequest, GetChannelInfoResponse> request = new ApiRequest<>(path, null, headers, queryParams, null, new TypeReference<>() {
        });

        return super.get(request);
    }

    /**
     * 채널 관리자를 조회할 수 있습니다.
     * <p>자세한 내용은
     * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/channel#undefined-1">공식 API 문서</a>를 참조하세요.</p>
     */
    public Response<GetChannelManagersResponse> getChannelManagers(String accessToken) {
        String path = "/open/v1/channels/streaming-roles";

        ApiRequest<EmptyRequest, GetChannelManagersResponse> request = new ApiRequest<>(path, accessToken, null, null, null, new TypeReference<>() {
        });

        return super.get(request);
    }

    /**
     * 채널의 팔로워 목록을 조회할 수 있습니다.
     * <p>자세한 내용은
     * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/channel#undefined-2">공식 API 문서</a>를 참조하세요.</p>
     * @param page 요청하는 페이지. 0부터 시작. 디폴트 값 : 0
     * @param size 조회할 카테고리 개수. 최소 1 ~ 최대 50 요청 가능. 디폴트 값 : 30
     */
    public Response<GetChannelFollowersResponse> getChannelFollowers(String accessToken, int page, int size) {
        String path = "/open/v1/channels/followers";

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("page", page);
        queryParams.put("size", size);

        ApiRequest<EmptyRequest, GetChannelFollowersResponse> request = new ApiRequest<>(path, accessToken, null, queryParams, null, new TypeReference<>() {
        });

        return super.get(request);
    }

    /**
     * 채널의 구독자 목록을 조회할 수 있습니다.
     * <p>자세한 내용은
     * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/channel#undefined-3">공식 API 문서</a>를 참조하세요.</p>
     * @param page 요청하는 페이지. 0부터 시작. 디폴트 값 : 0
     * @param size 조회할 카테고리 개수. 최소 1 ~ 최대 50 요청 가능. 디폴트 값 : 30
     * @param sort 정렬 방식<p>RECENT (최신 구독 순)<br/>LONGER (구독 개월 순)</p>
     */
    public Response<GetChannelSubscribersResponse> getChannelSubscribers(String accessToken, int page, int size, String sort) {
        String path = "/open/v1/channels/subscribers";

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("page", page);
        queryParams.put("size", size);
        queryParams.put("sort", sort);

        ApiRequest<EmptyRequest, GetChannelSubscribersResponse> request = new ApiRequest<>(path, accessToken, null, queryParams, null, new TypeReference<>() {
        });

        return super.get(request);
    }
}
