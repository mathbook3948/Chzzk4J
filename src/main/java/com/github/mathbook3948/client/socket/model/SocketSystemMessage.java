package com.github.mathbook3948.client.socket.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 시스템 메시지(공통) 모델.
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/session#undefined-10">공식 API 문서</a>를 참조하세요.</p>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SocketSystemMessage {

    /**
     * <p>시스템 메시지 종류</p>
     * <p>connected/subscribed/unsubscribed/revoked</p>
     */
    private String type;

    /**
     * <p>시스템 메시지 정보</p>
     */
    private Object data;
}