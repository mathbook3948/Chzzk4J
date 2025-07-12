package com.github.mathbook3948.client.socket;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mathbook3948.client.model.ApiRequest;
import com.github.mathbook3948.client.model.Response;
import com.github.mathbook3948.client.socket.model.EmptyRequest;
import com.github.mathbook3948.client.socket.model.EmptyResponse;
import com.github.mathbook3948.client.socket.model.SocketSystemMessage;
import com.github.mathbook3948.client.socket.model.SocketSystemMessageConnected;
import io.socket.client.Socket;
import org.eclipse.jetty.client.HttpClient;

import java.util.Map;
import java.util.function.Consumer;

/**
 * <p>세션 생성, 세션 목록 조회, 이벤트 구독 및 취소를 할 수 있습니다.</p>
 * <p>세션 API 중 아래 API Scope를 호출하려면 사용자 계정으로 인증하여 얻은 Access Token이 필요합니다.</p>
 * <p>API Scope는 채팅 메시지 조회, 후원 조회입니다.</p>
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/session">공식 API 문서</a>를 참조하세요.</p>
 */
public class SessionSocket extends AbstractSocketApi {

    private final ObjectMapper objectMapper;

    public SessionSocket(HttpClient httpClient, ObjectMapper objectMapper) {
        super(httpClient, objectMapper);

        this.objectMapper = objectMapper;
    }

    /**
     * 지정된 WebSocket URL로 소켓을 생성하고 연결합니다.
     * 연결 성공 시 SYSTEM 메시지를 수신하여 세션 키(sessionKey)를 추출하고,
     * 제공된 {@code onSessionKey} 콜백을 통해 전달합니다.
     *
     * <p>이 메서드는 소켓을 반환하지만, {@code onSessionKey}는 비동기적으로 호출되므로
     * 세션 키는 콜백 내에서만 유효합니다.</p>
     *
     * @param url          WebSocket 연결용 URL
     * @param onSessionKey 세션 연결 완료 시 세션 키를 처리할 콜백
     * @return 연결된 {@link Socket} 인스턴스
     * @throws IllegalArgumentException SYSTEM 메시지 형식이 잘못되었거나 세션 키가 누락된 경우
     */
    public Socket getSocket(String url, Consumer<String> onSessionKey) {
        SocketClient socketClient = new SocketClient(url, null);

        Socket socket = socketClient.getSocket();

        socket.on("SYSTEM", args -> {
            if (args.length == 0) throw new IllegalArgumentException("SYSTEM event received but args is null or empty");

            SocketSystemMessage msg = objectMapper.convertValue(args[0], SocketSystemMessage.class);

            if (msg == null)
                throw new IllegalArgumentException("SYSTEM event payload could not be deserialized");

            if (!msg.getType().equalsIgnoreCase("connected"))
                throw new IllegalArgumentException("SYSTEM event payload 'type' field is not 'connected'");

            if (msg.getData() == null)
                throw new IllegalArgumentException("SYSTEM event missing 'data' field");

            SocketSystemMessageConnected data = objectMapper.convertValue(msg.getData(), SocketSystemMessageConnected.class);

            if (data.getSessionKey() == null || data.getSessionKey().isEmpty())
                throw new IllegalArgumentException("SYSTEM event missing or empty 'sessionKey'");

            String sessionKey = data.getSessionKey();

            onSessionKey.accept(sessionKey);
        });

        return socket;
    }

    /**
     * <p>
     * 요청한 세션에 사용자의 채팅 이벤트를 구독합니다.<br/>
     * 구독이 완료될 경우 요청한 세션으로 이벤트 구독 메시지가 전달 됩니다.<br/>
     * 채팅 이벤트 구독 시, 구독한 채널에 채팅이 발생할 때 채팅 이벤트 메시지가 전달됩니다.
     * <p>관련 Scope : 채팅 메시지 조회</p>
     * <p>*세션당 최대 30개의 이벤트(채팅 및 후원)를 구독할 수 있습니다.</p>
     * <p>자세한 내용은
     * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/session#undefined-5">공식 API 문서</a>를 참조하세요.</p>
     * </p>
     */
    public Response<EmptyResponse> subscribeChatEvent(String sessionKey) {
        String path = "/open/v1/sessions/events/subscribe/chat";
        Map<String, String> queryParams = Map.of("sessionKey", sessionKey);

        ApiRequest<EmptyRequest, EmptyResponse> request = new ApiRequest<>(path, null, null, queryParams, null, new TypeReference<>() {
        });

        return super.post(request);
    }

    /**
     * <p>
     * 요청한 세션에 사용자의 채팅 이벤트를 구독 취소합니다.<br/>
     * 구독이 취소될 경우 요청한 세션으로 이벤트 구독 취소 메시지가 전달 됩니다.
     * <p>자세한 내용은
     * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/session#undefined-6">공식 API 문서</a>를 참조하세요.</p>
     * </p>
     */
    public Response<EmptyResponse> unsubscribeChatEvent(String sessionKey) {
        String path = "/open/v1/sessions/events/unsubscribe/chat";
        Map<String, String> queryParams = Map.of("sessionKey", sessionKey);

        ApiRequest<EmptyRequest, EmptyResponse> request = new ApiRequest<>(path, null, null, queryParams, null, new TypeReference<>() {
        });

        return super.post(request);
    }

    /**
     * <p>
     * 요청한 세션에 사용자의 후원 이벤트를 구독합니다.<br/>
     * 구독이 완료될 경우 요청한 세션으로 이벤트 구독 메시지가 전달 됩니다.<br/>
     * 이벤트 구독 시, 구독한 채널에 후원이 발생할 때 후원 이벤트 메시지가 전달됩니다.
     * <p>관련 Scope : 후원 조회</p>
     * <p>*세션당 최대 30개의 이벤트(채팅 및 후원)를 구독할 수 있습니다.</p>
     * <p>자세한 내용은
     * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/session#undefined-7">공식 API 문서</a>를 참조하세요.</p>
     * </p>
     */
    public Response<EmptyResponse> subscribeDonationEvent(String sessionKey) {
        String path = "/open/v1/sessions/events/subscribe/donation";
        Map<String, String> queryParams = Map.of("sessionKey", sessionKey);

        ApiRequest<EmptyRequest, EmptyResponse> request = new ApiRequest<>(path, null, null, queryParams, null, new TypeReference<>() {
        });

        return super.post(request);
    }

    /**
     * <p>
     * 요청한 세션에 사용자의 후원 이벤트를 구독 취소합니다.<br/>
     * 구독이 취소될 경우 요청한 세션으로 이벤트 구독 취소 메시지가 전달 됩니다.
     * <p>자세한 내용은
     * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/session#undefined-8">공식 API 문서</a>를 참조하세요.</p>
     * </p>
     */
    public Response<EmptyResponse> unsubscribeDonationEvent(String sessionKey) {
        String path = "/open/v1/sessions/events/unsubscribe/donation";
        Map<String, String> queryParams = Map.of("sessionKey", sessionKey);

        ApiRequest<EmptyRequest, EmptyResponse> request = new ApiRequest<>(path, null, null, queryParams, null, new TypeReference<>() {
        });

        return super.post(request);
    }
}
