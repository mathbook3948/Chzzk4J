package com.github.mathbook3948.client.api.model.channel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 채널 정보 조회 요청 모델.
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/channel#undefined">공식 API 문서</a>를 참조하세요.</p>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetChannelInfoResponse {

    /**
     * 요청한 채널 정보 목록. 일치하는 채널을 찾지 못할 경우 결과 미반환
     * */
    private List<Channel> data;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Channel {

        /**
         * 채널 식별자
         * */
        private String channelId;

        /**
         * 채널 이름
         * */
        private String channelName;

        /**
         * 채널 이미지 URL
         * */
        private String channelImageUrl;

        /**
         * 채널의 팔로워 수
         * */
        private Integer followerCount;
    }
}
