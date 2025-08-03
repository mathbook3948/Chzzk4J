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
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/channel#undefined-1">공식 API 문서</a>를 참조하세요.</p>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetChannelManagersResponse {

    /**
     * 요청한 채널 관리자 목록
     * */
    private List<ChannelManager> data;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChannelManager {

        /**
         * 관리자 채널 식별자
         * */
        private String managerChannelId;

        /**
         * 관리자 채널 이름
         * */
        private String managerChannelName;

        /**
         * 관리자 역할
         * <p>
         *     STREAMING_CHANNEL_OWNER - 채널 소유자<br/>
         *     STREAMING_CHANNEL_MANAGER - 채널 관리자<br/>
         *     STREAMING_CHAT_MANAGER - 채팅 운영자<br/>
         *     STREAMING_SETTLEMENT_MANAGER - 정산 관리자<br/>
         * </p>
         * */
        private String userRole;

        /**
         * 등록일
         * */
        private Date createdDate;
    }
}
