package com.github.mathbook3948.api.model.channel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 채널 정보 조회 요청 모델.
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/channel#undefined">공식 API 문서</a>를 참조하세요.</p>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetChannelInfoRequest {

    /**
     * 조회할 채널 ID 목록. 최대 20개까지 요청 가능
     */
    private String[] channelIds;
}
