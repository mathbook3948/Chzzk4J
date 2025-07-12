package com.github.mathbook3948.client.socket.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 구독 이벤트 메시지(채팅 이벤트 메시지) 모델.
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/session#message-event-subscribe-chat">공식 API 문서</a>를 참조하세요.</p>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SocketSubscribedChatMessage {

    /**
     * 이벤트 채널 ID(채널 식별자)
     * */
    private String channelId;

    /**
     * 채팅 메시지 작성자 채널 ID
     * */
    private String senderChannelId;

    /**
     * 채팅 메시지 작성자 프로필 정보
     * */
    private Profile profile;

    /**
     * 채팅 메시지 내용
     * */
    private String content;

    /**
     * 사용된 치지직 이모티콘 정보
     * <p>key : 치지직 이모티콘 식별자</p>
     * <p>value : 치지직 이모티콘 URL</p>
     * */
    private Map<String, String> emojis;

    private Long messageTime;

    public static class Profile {
        private String nickname;

        //TODO Object 구체 명시 필요
        /**
         * 배지
         * */
        private Object[] badges;

        /**
         * 인증여부
         * */
        private boolean verifiedMark;
    }
}