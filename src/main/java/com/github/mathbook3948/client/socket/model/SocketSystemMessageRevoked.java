package com.github.mathbook3948.client.socket.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 시스템 메시지(이벤트 권한 취소 메시지) data 모델.
 * <p>
 * 사용자의 동의 철회, 스코프 변경 등 권한 회수로 이벤트 구독이 취소 되었을 때 전달됩니다.
 * </p>
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/session#undefined-12">공식 API 문서</a>를 참조하세요.</p>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SocketSystemMessageRevoked {

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
