package com.github.mathbook3948.client.api.model.channel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 채널 관리자를 조회 요청 모델.
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/channel#undefined-3">공식 API 문서</a>를 참조하세요.</p>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetChannelSubscribersResponse {

    /**
     * 요청한 채널의 구독자 목록
     */
    private List<Channel> data;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Channel {

        /**
         * 구독자 채널 식별자
         */
        private String channelId;

        /**
         * 구독자 채널 이름
         */
        private String channelName;

        /**
         * 구독 개월 수
         * */
        private Integer month;

        /**
         * 구독 상품
         * <p>
         *     1 (티어1 구독)<br/>
         *     2 (티어2 구독)
         * </p>
         */
        private Integer tierNo;

        /**
         * 팔로우 일자
         */
        private Date createdDate;
    }
}
