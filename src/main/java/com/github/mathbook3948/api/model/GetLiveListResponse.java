package com.github.mathbook3948.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 채팅 메시지 전송 응답 모델.
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/chat#undefined">공식 API 문서</a>를 참조하세요.</p>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetLiveListResponse {

    /**
     * 라이브 목록 결과. 시청자 수 높은 순 정렬
     * */
    private List<LiveData> data;

    private Page page;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LiveData {
        /**
         * 라이브 식별자
         * */
        private Integer liveId;

        /**
         * 라이브 제목
         * */
        private String liveTitle;

        /**
         * 라이브 썸네일로 사용되는 이미지 URL
         * */
        private String liveThumbnailImageUrl;

        /**
         * 라이브 현재 시청자 수
         * */
        private Integer concurrentUserCount;

        /**
         * 라이브 시작 시간
         * */
        private String openDate;

        /**
         * 연령 제한 설정 여부
         * */
        private Boolean adult;

        /**
         * 라이브에 설정된 태그 목록
         * */
        private String[] tags;

        /**
         * <p>카테고리 종류</p>
         * <p>GAME/SPORTS/ETC</p>
         * */
        private String categoryType;

        /**
         * 라이브 카테고리 식별자
         * */
        private String liveCategory;

        /**
         * 라이브 카테고리 이름
         * */
        private String liveCategoryValue;

        /**
         * 채널 ID(채널 식별자)
         * */
        private String channelId;

        /**
         * 채널명
         * */
        private String channelName;

        /**
         * 채널 이미지 URL
         * */
        private String channelImageUrl;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Page {
        /**
         * 다음 목록 호출을 위한 값
         * */
        private String next;
    }
}