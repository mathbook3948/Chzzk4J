package com.github.mathbook3948.client.api.model.chat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 채팅 설정 조회 응답 모델.
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/chat#undefined-2">공식 API 문서</a>를 참조하세요.</p>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetChatSettingsResponse {

    /**
     * 본인인증 여부 설정 조건.
     * NONE: 제한 없음,
     * REAL_NAME: 네이버 본인인증한 시청자만 채팅 가능
     */
    private String chatAvailableCondition;

    /**
     * 채팅 참여 범위 설정 조건.
     * ALL: 전체 공개,
     * FOLLOWER: 팔로워 전용,
     * MANAGER: 운영자 전용,
     * SUBSCRIBER: 구독자 전용
     */
    private String chatAvailableGroup;

    /**
     * FOLLOWER 모드일 때 적용된 최소 팔로우 경과 시간 (분 단위)
     */
    private int minFollowerMinute;

    /**
     * FOLLOWER 모드에서 구독자가 제한 없이 채팅 가능하도록 허용할지 여부
     */
    private boolean allowSubscriberInFollowerMode;
}
