package com.github.mathbook3948.client.socket.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 시스템 메시지(이벤트 구독 취소 메시지) data 모델.
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/session#message-event-unsubscribe">공식 API 문서</a>를 참조하세요.</p>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SocketSystemMessageUnsubscribed {

    /**
     * <p>이벤트 종류</p>
     * <p>CHAT/DONATION/SUBSCRIPTION</p>
     * */
    private String eventType;

    /**
     * 이벤트 채널 ID(채널 식별자)
     * */
    private String channelId;
}
