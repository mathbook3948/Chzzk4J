package com.github.mathbook3948.client.unofficial.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mathbook3948.client.api.model.EmptyRequest;
import com.github.mathbook3948.client.model.ApiRequest;
import com.github.mathbook3948.client.model.Response;
import com.github.mathbook3948.client.unofficial.api.model.GetChannelLiveStatusResponse;
import com.github.mathbook3948.config.ChzzkClientConfig;
import org.eclipse.jetty.client.HttpClient;

public class UnOfficialChannelApi extends AbstractUnOfficialApi {
    public UnOfficialChannelApi(HttpClient client, ObjectMapper objectMapper) {
        super(client, objectMapper);
    }

    /**
     * 해당 채널의 라이브 관련 정보를 가져옵니다.
     * <p><strong>비공식 API 입니다. 언제든 사용이 중단될 수 있습니다.</strong></p>
     */
    public Response<GetChannelLiveStatusResponse> getChannelLiveStatus(String channelId) {
        String path = String.format("/polling/%s/channels/%s/live-status", ChzzkClientConfig.API_BASE_URL_VERSION, channelId);

        ApiRequest<EmptyRequest, GetChannelLiveStatusResponse> request = new ApiRequest<>(path, null, null, null, null, new TypeReference<>() {
        });

        return super.get(request);
    }
}
