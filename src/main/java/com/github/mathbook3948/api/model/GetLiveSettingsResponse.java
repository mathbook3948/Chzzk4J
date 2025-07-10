package com.github.mathbook3948.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 방송 설정 조회 응답 모델.
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/live#undefined-2">공식 API 문서</a>를 참조하세요.</p>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetLiveSettingsResponse {

    /**
     * 라이브 제목
     * */
    private String defaultLiveTitle;

    /**
     * 라이브 카테고리
     * */
    private Category category;

    /**
     * 라이브 태그 목록
     * */
    private String[] tags;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Category {

        /**
         * <p>카테고리 종류</p>
         * <p>GAME/SPORTS/ETC</p>
         * */
        private String categoryType;

        /**
         * 카테고리 식별자
         * */
        private String categoryId;

        /**
         * 카테고리 이름
         * */
        private String categoryValue;

        /**
         * 카테고리 이미지 URL
         * */
        private String posterImageUrl;
    }
}