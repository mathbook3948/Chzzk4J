package com.github.mathbook3948.client.api.model.live;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 라이브 목록 조회 요청 모델.
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/live#undefined">공식 API 문서</a>를 참조하세요.</p>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetLiveListRequest {

    /**
     * 조회할 라이브 개수. 최소 1 ~ 최대 20 요청 가능
     * <p>
     * 디폴트 값 : 20
     * </p>
     * */
    private Integer size;

    /**
     * <p>
     * 다음 목록을 호출하기 위한 값입니다. api 응답 중 page.next 값을 통해 호출 가능
     * </p>
     * */
    private String next;
}
