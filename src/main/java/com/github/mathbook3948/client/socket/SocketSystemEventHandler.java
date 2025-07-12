package com.github.mathbook3948.client.socket;

import com.github.mathbook3948.client.socket.model.SocketSystemMessageConnected;
import com.github.mathbook3948.client.socket.model.SocketSystemMessageRevoked;
import com.github.mathbook3948.client.socket.model.SocketSystemMessageSubscribed;
import com.github.mathbook3948.client.socket.model.SocketSystemMessageUnsubscribed;
import lombok.Getter;

import java.util.function.Consumer;

/**
 * WebSocket의 SYSTEM 메시지 유형별 콜백을 정의하는 핸들러 클래스.
 * <p>각 이벤트 유형에 대응하는 {@link Consumer}를 지정하여, 시스템 메시지를 수신했을 때 처리할 수 있도록 합니다.</p>
 *
 * <ul>
 *     <li>{@code onConnect} – 연결 완료 메시지 수신 시 호출</li>
 *     <li>{@code onSubscribe} – 구독 완료 메시지 수신 시 호출</li>
 *     <li>{@code onUnsubscribe} – 구독 취소 메시지 수신 시 호출</li>
 *     <li>{@code onRevoked} – 권한 철회 메시지 수신 시 호출</li>
 *     <li>{@code onSessionKey} – {@code onConnect}가 정의되지 않은 경우, 연결 메시지에서 세션 키만 전달</li>
 * </ul>
 */
@Getter
public class SocketSystemEventHandler {

    private final Consumer<SocketSystemMessageConnected> onConnect;

    private final Consumer<SocketSystemMessageSubscribed> onSubscribe;

    private final Consumer<SocketSystemMessageUnsubscribed> onUnsubscribe;

    private final Consumer<SocketSystemMessageRevoked> onRevoked;

    private final Consumer<String> onSessionKey;

    public SocketSystemEventHandler(Consumer<SocketSystemMessageConnected> onConnect, Consumer<SocketSystemMessageSubscribed> onSubscribe, Consumer<SocketSystemMessageUnsubscribed> onUnsubscribe, Consumer<SocketSystemMessageRevoked> onRevoked, Consumer<String> onSessionKey) {
        this.onConnect = onConnect;
        this.onSubscribe = onSubscribe;
        this.onUnsubscribe = onUnsubscribe;
        this.onRevoked = onRevoked;
        this.onSessionKey = onSessionKey;
    }
}
