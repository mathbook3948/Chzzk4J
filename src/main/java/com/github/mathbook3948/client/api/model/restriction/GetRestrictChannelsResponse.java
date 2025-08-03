package com.github.mathbook3948.client.api.model.restriction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 활동 제한 목록 응답 모델.
 * <p>자세한 내용은
 * <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/restriction#undefined-2">공식 API 문서</a>를 참조하세요.</p>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetRestrictChannelsResponse {

    /**
     * 활동 제한 대상 channelId
     * */
    private String restrictedChannelId;

    /**
     * 활동 제한 대상 채널명
     * */
    private String restrictedChannelName;

    /**
     * 활동 제한 일자
     * */
    private Date createdDate;

    /**
     * 활동 제한 해제 일자
     * */
    private Date releaseDate;
}
