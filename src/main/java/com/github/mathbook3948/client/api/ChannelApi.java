package com.github.mathbook3948.client.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mathbook3948.client.api.model.EmptyRequest;
import com.github.mathbook3948.client.api.model.channel.GetChannelInfoRequest;
import com.github.mathbook3948.client.api.model.channel.GetChannelInfoResponse;
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
}
