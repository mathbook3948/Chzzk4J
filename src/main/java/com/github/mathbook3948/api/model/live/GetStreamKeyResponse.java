package com.github.mathbook3948.api.model.live;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 방송 스트림키 조회 응답 모델.
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/chat#undefined-1">공식 API 문서</a>를 참조하세요.</p>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetStreamKeyResponse {

    /**
     * 스트림키 값
     * */
    private String streamKey;
}