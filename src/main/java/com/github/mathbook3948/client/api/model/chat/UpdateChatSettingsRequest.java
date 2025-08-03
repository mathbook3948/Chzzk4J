package com.github.mathbook3948.client.api.model.chat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 채팅 설정 변경 요청 모델.
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/chat#undefined-3">공식 API 문서</a>를 참조하세요.</p>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateChatSettingsRequest {

    /**
     * 본인인증 여부 설정 조건
     * NONE: 제한 없음,
     * REAL_NAME: 네이버 본인인증한 시청자만 채팅 가능
     */
    private String chatAvailableCondition;

    /**
     * 채팅 참여 범위 설정 조건
     * ALL: 전체 공개,
     * FOLLOWER: 팔로워 전용,
     * MANAGER: 운영자 전용,
     * SUBSCRIBER: 구독자 전용
     */
    private String chatAvailableGroup;

    /**
     * FOLLOWER 모드 설정된 경우 적용된 최소 팔로잉 기간 조건
     * <p>
     * 허용 값: 0, 5, 10, 30, 60, 1440, 10080, 43200
     * </p>
     */
    private int minFollowerMinute;

    /**
     * FOLLOWER 모드 설정된 경우 구독자는 최소 팔로잉 기간 조건 대상에서 제외 허용 할지 여부
     */
    private boolean allowSubscriberInFollowerMode;

    /**
     * 시청자의 채팅 전송 간격 (초)
     * <p>
     * 허용 값: 0 (저속모드 Off), 3, 5, 10, 30, 60, 120, 300 값만 허용
     * </p>
     */
    private Integer chatSlowModeSec;

    /**
     * 이모티콘 모드 사용 여부
     * */
    private Boolean chatEmojiMode;
}
