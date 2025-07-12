package com.github.mathbook3948.client.api.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 유저 정보 조회 응답 모델.
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/user#undefined">공식 API 문서</a>를 참조하세요.</p>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserInfoResponse {

    /**
     * 사용자 식별자
     * */
    private String channelId;

    /**
     * 사용자명
     * */
    private String channelName;
}
