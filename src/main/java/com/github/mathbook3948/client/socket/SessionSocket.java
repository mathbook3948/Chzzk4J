package com.github.mathbook3948.client.socket;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mathbook3948.client.model.ApiRequest;
import com.github.mathbook3948.client.model.Response;
import com.github.mathbook3948.client.socket.model.*;
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
     * <p>
     * 연결 성공 시 SYSTEM 메시지를 수신하여 메시지 타입에 따라 {@link SocketSystemEventHandler} 내 정의된 콜백이 실행됩니다.
     * </p>
     *
     * <p>처리되는 SYSTEM 메시지 타입은 다음과 같습니다:</p>
     * <ul>
     *   <li>{@code connected} – {@code getOnConnect()}가 정의된 경우 해당 콜백 실행, 그렇지 않으면 {@code getOnSessionKey()} 실행</li>
     *   <li>{@code subscribed} – {@code getOnSubscribe()} 콜백 실행</li>
     *   <li>{@code unsubscribed} – {@code getOnUnsubscribe()} 콜백 실행</li>
     *   <li>{@code revoked} – {@code getOnRevoked()} 콜백 실행</li>
     * </ul>
     *
     * <p>{@code connected} 메시지에서 세션 키만 필요할 경우 {@code getOnConnect()} 대신 {@code getOnSessionKey()}만 지정해도 됩니다.</p>
     *
     * @param url                WebSocket 연결용 URL
     * @param systemEventHandler SYSTEM 메시지 처리용 핸들러
     * @return 연결된 {@link Socket} 인스턴스
     * @throws IllegalArgumentException SYSTEM 메시지가 비어있거나, 역직렬화에 실패하거나, 지원하지 않는 {@code type} 값을 포함한 경우
     */
    public Socket getSocket(String url, SocketSystemEventHandler systemEventHandler) {
        SocketClient socketClient = new SocketClient(url, null);

        Socket socket = socketClient.getSocket();

        socket.on("SYSTEM", args -> {
            if (args.length == 0) throw new IllegalArgumentException("SYSTEM event received but args is null or empty");

            SocketSystemMessage msg = objectMapper.convertValue(args[0], SocketSystemMessage.class);

            if (msg == null)
                throw new IllegalArgumentException("SYSTEM event payload could not be deserialized");

            switch (msg.getType()) {
                case "connected": {
                    Consumer<SocketSystemMessageConnected> handler = systemEventHandler.getOnConnect();

                    if (handler != null) {
                        SocketSystemMessageConnected connected = objectMapper.convertValue(msg.getData(), SocketSystemMessageConnected.class);
                        handler.accept(connected);
                    }

                    Consumer<String> onSessionKey = systemEventHandler.getOnSessionKey();
                    String sessionKey = objectMapper.convertValue(msg.getData(), SocketSystemMessageConnected.class).getSessionKey();
                    onSessionKey.accept(sessionKey);
                    break;
                }
                case "subscribed": {
                    Consumer<SocketSystemMessageSubscribed> handler = systemEventHandler.getOnSubscribe();

                    if (handler != null) {
                        SocketSystemMessageSubscribed connected = objectMapper.convertValue(msg.getData(), SocketSystemMessageSubscribed.class);
                        handler.accept(connected);
                    }
                    break;
                }
                case "unsubscribed": {
                    Consumer<SocketSystemMessageUnsubscribed> handler = systemEventHandler.getOnUnsubscribe();

                    if (handler != null) {
                        SocketSystemMessageUnsubscribed connected = objectMapper.convertValue(msg.getData(), SocketSystemMessageUnsubscribed.class);
                        handler.accept(connected);
                    }
                    break;
                }
                case "revoked": {
                    Consumer<SocketSystemMessageRevoked> handler = systemEventHandler.getOnRevoked();

                    if (handler != null) {
                        SocketSystemMessageRevoked connected = objectMapper.convertValue(msg.getData(), SocketSystemMessageRevoked.class);
                        handler.accept(connected);
                    }
                    break;
                }
                default:
                    throw new IllegalArgumentException("SYSTEM event payload 'type' field is not valid");
            }
        });

        return socket;
    }

    /**
     * WebSocket으로 전달되는 {@code CHAT} 이벤트 메시지를 수신하기 위한 핸들러를 등록합니다.
     * <p>
     * 채팅 이벤트 구독이 완료된 후, 구독한 채널에서 채팅이 발생하면 해당 이벤트가 {@code CHAT} 메시지로 전달됩니다.
     * {@code onChatEvent} 콜백에 전달합니다.
     * </p>
     *
     * @param socket      이벤트 핸들러를 등록할 {@link Socket} 인스턴스
     * @param onChatEvent 수신된 채팅 이벤트 메시지를 처리할 콜백
     * @throws IllegalArgumentException {@code CHAT} 이벤트 메시지가 null이거나 형식이 잘못된 경우
     */
    public void addChatEventHandler(Socket socket, Consumer<SocketSubscribedChatMessage> onChatEvent) {
        socket.on("CHAT", args -> {
            if (args.length == 0) throw new IllegalArgumentException("CHAT event received but args is null or empty");

            SocketSubscribedChatMessage msg = objectMapper.convertValue(args[0], SocketSubscribedChatMessage.class);

            onChatEvent.accept(msg);
        });
    }

    /**
     * WebSocket으로 전달되는 {@code DONATION} 이벤트 메시지를 수신하기 위한 핸들러를 등록합니다.
     * <p>
     * 후원 이벤트 구독이 완료된 후, 구독한 채널에서 후원이 발생하면 해당 이벤트가 {@code DONATION} 메시지로 전달됩니다.
     * </p>
     *
     * @param socket          이벤트 핸들러를 등록할 {@link Socket} 인스턴스
     * @param onDonationEvent 수신된 후원 이벤트 메시지를 처리할 콜백
     * @throws IllegalArgumentException {@code DONATION} 이벤트 메시지가 null이거나 형식이 잘못된 경우
     */
    public void addDonationEventHandler(Socket socket, Consumer<SocketSubscribedDonationMessage> onDonationEvent) {
        socket.on("DONATION", args -> {
            if (args.length == 0)
                throw new IllegalArgumentException("DONATION event received but args is null or empty");

            SocketSubscribedDonationMessage msg = objectMapper.convertValue(args[0], SocketSubscribedDonationMessage.class);

            onDonationEvent.accept(msg);
        });
    }

    /**
     * 요청한 세션에 사용자의 채팅 이벤트를 구독합니다.<br/>
     * 구독이 완료될 경우 요청한 세션으로 이벤트 구독 메시지가 전달 됩니다.<br/>
     * 채팅 이벤트 구독 시, 구독한 채널에 채팅이 발생할 때 채팅 이벤트 메시지가 전달됩니다.
     * <p>관련 Scope : 채팅 메시지 조회</p>
     * <p>*세션당 최대 30개의 이벤트(채팅 및 후원)를 구독할 수 있습니다.</p>
     * <p>자세한 내용은
     * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/session#undefined-5">공식 API 문서</a>를 참조하세요.</p>
     */
    public Response<EmptyResponse> subscribeChatEvent(String sessionKey) {
        String path = "/open/v1/sessions/events/subscribe/chat";
        Map<String, Object> queryParams = Map.of("sessionKey", sessionKey);

        ApiRequest<EmptyRequest, EmptyResponse> request = new ApiRequest<>(path, null, null, queryParams, null, new TypeReference<>() {
        });

        return super.post(request);
    }

    /**
     * 요청한 세션에 사용자의 채팅 이벤트를 구독 취소합니다.<br/>
     * 구독이 취소될 경우 요청한 세션으로 이벤트 구독 취소 메시지가 전달 됩니다.
     * <p>자세한 내용은
     * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/session#undefined-6">공식 API 문서</a>를 참조하세요.</p>
     */
    public Response<EmptyResponse> unsubscribeChatEvent(String sessionKey) {
        String path = "/open/v1/sessions/events/unsubscribe/chat";
        Map<String, Object> queryParams = Map.of("sessionKey", sessionKey);

        ApiRequest<EmptyRequest, EmptyResponse> request = new ApiRequest<>(path, null, null, queryParams, null, new TypeReference<>() {
        });

        return super.post(request);
    }

    /**
     * 요청한 세션에 사용자의 후원 이벤트를 구독합니다.<br/>
     * 구독이 완료될 경우 요청한 세션으로 이벤트 구독 메시지가 전달 됩니다.<br/>
     * 이벤트 구독 시, 구독한 채널에 후원이 발생할 때 후원 이벤트 메시지가 전달됩니다.
     * <p>관련 Scope : 후원 조회</p>
     * <p>*세션당 최대 30개의 이벤트(채팅 및 후원)를 구독할 수 있습니다.</p>
     * <p>자세한 내용은
     * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/session#undefined-7">공식 API 문서</a>를 참조하세요.</p>
     */
    public Response<EmptyResponse> subscribeDonationEvent(String sessionKey) {
        String path = "/open/v1/sessions/events/subscribe/donation";
        Map<String, Object> queryParams = Map.of("sessionKey", sessionKey);

        ApiRequest<EmptyRequest, EmptyResponse> request = new ApiRequest<>(path, null, null, queryParams, null, new TypeReference<>() {
        });

        return super.post(request);
    }

    /**
     * 요청한 세션에 사용자의 후원 이벤트를 구독 취소합니다.<br/>
     * 구독이 취소될 경우 요청한 세션으로 이벤트 구독 취소 메시지가 전달 됩니다.
     * <p>자세한 내용은
     * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/session#undefined-8">공식 API 문서</a>를 참조하세요.</p>
     */
    public Response<EmptyResponse> unsubscribeDonationEvent(String sessionKey) {
        String path = "/open/v1/sessions/events/unsubscribe/donation";
        Map<String, Object> queryParams = Map.of("sessionKey", sessionKey);

        ApiRequest<EmptyRequest, EmptyResponse> request = new ApiRequest<>(path, null, null, queryParams, null, new TypeReference<>() {
        });

        return super.post(request);
    }
}
