package com.github.mathbook3948.api.model.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 카테고리 검색 요청 모델.
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/category#undefined">공식 API 문서</a>를 참조하세요.</p>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchCategoriesRequest {

    /**
     * 조회할 카테고리 개수. 최소 1 ~ 최대 50 요청 가능
     * <p>
     * 디폴트 값 : 20
     * </p>
     */
    private Integer size;

    /**
     * <p>
     * 검색할 카테고리 이름. 해당 값을 포함하는 카테고리 목록 반환
     * </p>
     */
    private String query;
}
