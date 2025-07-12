package com.github.mathbook3948.client.socket.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 시스템 메시지(이벤트 구독 메시지) data 모델.
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/session#message-event-subscribe">공식 API 문서</a>를 참조하세요.</p>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SocketSystemMessageSubscribed {

    /**
     * <p>이벤트 종류</p>
     * <p>CHAT/DONATION</p>
     * */
    private String eventType;

    /**
     * 이벤트 채널 ID(채널 식별자)
     * */
    private String channelId;
}
