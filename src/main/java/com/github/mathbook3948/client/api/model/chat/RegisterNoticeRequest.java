package com.github.mathbook3948.client.api.model.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 채팅 공지 등록 요청 모델.
 * <p>
 * {@code message} 또는 {@code messageId} 중 하나만 사용됩니다.
 * {@code messageId}가 우선 적용되며, 없을 경우 {@code message}의 내용으로 새 공지를 생성합니다.
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/chat#undefined-1">공식 API 문서</a>를 참조하세요.</p>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterNoticeRequest {

    /**
     * 공지로 등록할 메시지 내용 (최대 100자).
     * {@code messageId}가 없을 경우 사용됩니다.
     */
    private String message;

    /**
     * 기존 채팅 메시지 ID.
     * 해당 ID를 공지로 등록하며, 존재할 경우 {@code message}는 무시됩니다.
     */
    private String messageId;
}
