package com.github.mathbook3948.client.websocket;

import com.github.mathbook3948.client.model.Response;
import com.github.mathbook3948.client.api.model.session.CreateClientSessionResponse;
import org.eclipse.jetty.websocket.client.WebSocketClient;

/**
 * <p>세션 생성, 세션 목록 조회, 이벤트 구독 및 취소를 할 수 있습니다.</p>
 * <p>세션 API 중 아래 API Scope를 호출하려면 사용자 계정으로 인증하여 얻은 Access Token이 필요합니다.</p>
 * <p>API Scope는 채팅 메시지 조회, 후원 조회입니다.</p>
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/session">공식 API 문서</a>를 참조하세요.</p>
 */
public class SessionWebsocket extends AbstractWebsocket{

    private final String clientId;

    private final String clientSecret;

    public SessionWebsocket(WebSocketClient webSocketClient, String clientId, String clientSecret) {
        super(webSocketClient);

        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public Response<CreateClientSessionResponse> createClientSession() {
        String path = "/open/v1/sessions";
        return null;
    }

}
