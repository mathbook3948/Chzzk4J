package com.github.mathbook3948.client.api.model.session;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 세션 생성(클라이언트) 요청 모델.
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/session#undefined-2">공식 API 문서</a>를 참조하세요.</p>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetClientSessionListRequest {

    /**
     * 조회할 카테고리 개수. 최소 1 ~ 최대 50 요청 가능
     * <p>
     * 디폴트 값 : 20
     * </p>
     */
    private Integer size;

    /**
     * 조회할 페이지. 0부터 조회 가능
     * <p>
     * 디폴트 값 : 0
     * </p>
     */
    private String page;
}
