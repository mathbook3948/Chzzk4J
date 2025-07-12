package com.github.mathbook3948.client.api.model.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 카테고리 검색 응답 모델.
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/category#undefined">공식 API 문서</a>를 참조하세요.</p>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchCategoryResponse {

    /**
     * 카테고리 목록 결과
     * */
    private List<Category> data;

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