package com.github.mathbook3948.client.socket.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 구독 이벤트 메시지(후원 이벤트 메시지) 모델.
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/session#undefined-13">공식 API 문서</a>를 참조하세요.</p>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SocketSubscribedDonationMessage {

    /**
     * <p>후원 종류</p>
     * <p>CHAT/VIDEO</p>
     */
    private String donationType;

    /**
     * 이벤트 채널 ID(채널 식별자)
     */
    private String channelId;

    /**
     * 채팅 메시지 작성자 채널 ID
     */
    private String donatorChannelId;

    /**
     * 후원자 닉네임
     */
    private String donatorNickname;

    /**
     * 후원 금액 (원)
     */
    private String payAmount;

    /**
     * 후원 메시지 내용
     */
    private String donationText;

    /**
     * 사용된 치지직 이모티콘 정보
     * <p>key : 치지직 이모티콘 식별자</p>
     * <p>value : 치지직 이모티콘 URL</p>
     */
    private Map<String, String> emojis;
}