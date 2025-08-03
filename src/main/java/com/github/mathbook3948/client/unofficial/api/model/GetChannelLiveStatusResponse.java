package com.github.mathbook3948.client.unofficial.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 채널 라이브 정보 조회 응답 모델.
 * <p><strong>비공식 API 입니다. 언제든 사용이 중단될 수 있습니다.</strong></p>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetChannelLiveStatusResponse {
    /**
     * 채널 구분자
     * */
    private String channelId;

    /**
     * 생방송 제목
     * */
    private String liveTitle;

    /**
     * 현재 생방송 상태
     * <p>CLOSED/OPEN</p>
     * */
    private String status;

    /**
     * 동시 시청자 수
     * */
    private Integer concurrentUserCount;
    private Integer accumulateCount;
    private Boolean paidPromotion;
    private Boolean adult;
    private Boolean krOnlyViewing;

    /**
     * yyyy-MM-dd HH:mm:ss 형식
     */
    private String openDate;

    /**
     * yyyy-MM-dd HH:mm:ss 형식
     */
    private String closeDate;
    private Boolean clipActive;
    private String chatChannelId;
    private List<String> tags;
    private String categoryType;
    private String liveCategory;
    private String liveCategoryValue;
    private String livePollingStatusJson;
    private String faultStatus;
    private String userAdultStatus;
    private Boolean abroadCountry;
    private String blindType;
    private Boolean chatActive;
    private String chatAvailableGroup;
    private String chatAvailableCondition;
    private Integer minFollowerMinute;
    private Boolean allowSubscriberInFollowerMode;
    private Integer chatSlowModeSec;
    private Boolean chatEmojiMode;
    private Boolean chatDonationRankingExposure;
    private String dropsCampaignNo;
    private List<String> liveTokenList;
    private String watchPartyNo;
    private String watchPartyTag;
    private Boolean timeMachineActive;
    private String lastAdultReleaseDate;
    private String lastKrOnlyViewingReleaseDate;
    private String createdAt;
    private String updatedAt;
    private String channelName;
    private String tvAppViewingPolicyType;
    private Boolean logPowerActive;
    private Boolean logPowerRankingExposure;

    private PlayerRecommendContent playerRecommendContent;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class PlayerRecommendContent {
        private List<Object> categoryLives;
        private List<Object> channelLatestVideos;
    }
}
