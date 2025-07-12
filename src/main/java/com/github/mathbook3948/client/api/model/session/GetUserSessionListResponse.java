package com.github.mathbook3948.client.api.model.session;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 세션 생성(유저) 응답 모델.
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/session#undefined-4">공식 API 문서</a>를 참조하세요.</p>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserSessionListResponse {

    /**
     * 세션 목록 결과
     * */
    private List<Session> data;

    public static class Session {

        /**
         * 세션 식별자
         * */
        private String sessionKey;

        /**
         * 연결 시간
         * */
        private String connectedDate;

        /**
         * 연결 해제 시간
         * */
        private String disconnectedDate;

        /**
         * 구독 이벤트 목록
         * */
        private List<Subscribe> subscribedEvents;

        public static class Subscribe {

            /**
             * <p>이벤트 종류</p>
             * <p>CHAT/DONATION</p>
             * */
            private String eventType;

            /**
             * 이벤트 채널 ID(채널 식별자)
             * */
            private String channelId;
        }
    }
}
