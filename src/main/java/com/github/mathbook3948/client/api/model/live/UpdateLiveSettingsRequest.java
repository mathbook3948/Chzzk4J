package com.github.mathbook3948.client.api.model.live;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 방송 설정 변경 요청 모델.
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/live#undefined-3">공식 API 문서</a>를 참조하세요.</p>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateLiveSettingsRequest {

    /**
     * 라이브 제목
     * */
    private String defaultLiveTitle;

    /**
     * <p>카테고리 종류</p>
     * <p>GAME/SPORTS/ETC</p>
     * */
    private String categoryType;

    /**
     * 카테고리 식별자. 유효한 카테고리 종류로만 설정 가능. ""으로 전송할 경우 카테고리 설정 제거
     * */
    private String categoryId;

    /**
     * 라이브 태그 목록. empty list로 전송할 경우 태그 설정 제거. 공백 및 특수문자 비허용
     * */
    private String[] tags;
}