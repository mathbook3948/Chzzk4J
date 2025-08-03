package com.github.mathbook3948.client.socket.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 구독 이벤트 메시지(구독 이벤트 메시지) 모델.
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/session#message-event-subscribe-subscription">공식 API 문서</a>를 참조하세요.</p>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SocketSubscribedSubscriptionMessage {

    /**
     * 이벤트 채널 ID(채널 식별자)
     */
    private String channelId;

    /**
     * 구독자 채널 ID
     */
    private String subscriberChannelId;

    /**
     * 구독자 닉네임
     */
    private String subscriberNickname;

    /**
     * 구독 상품
     * <p>
     *     1 (티어1 구독)<br/>
     *     2 (티어2 구독)
     * </p>
     */
    private Integer tierNo;

    /**
     * 구독 브랜드명
     */
    private String tierName;

    /**
     * 사용된구독 기간치지직 이모티콘 정보
     * */
    private Integer month;
}