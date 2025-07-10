package com.github.mathbook3948.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * CHZZK Open API 공통 응답 형식을 반영한 Generic Response 클래스.
 *
 * <p>CHZZK API 명세에 따라 모든 응답은 다음의 구조를 가짐:</p>
 * <pre>
 * {
 *   "code": 200,
 *   "message": null,
 *   "content": {responseBody}
 * }
 * </pre>
 *
 * <p>에러 발생 시:</p>
 * <pre>
 * {
 *   "code": 401,
 *   "message": "INVALID_TOKEN"
 * }
 * </pre>
 *
 * @param <T> 응답 본문의 데이터 타입
 * @see <a href="https://chzzk.gitbook.io/chzzk/chzzk-api/tips">CHZZK Developers</a>
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    /**
     * 응답 코드 (예: 200, 401, 404 등).
     * <p>CHZZK API Error Code 참조</p>
     */
    private Integer code;

    /**
     * 응답 메시지. 성공 시 null 또는 빈 문자열, 실패 시 오류 코드 문자열 포함.
     */
    private String message;

    /**
     * 응답 본문 데이터.
     * 성공 시 응답 객체 포함, 실패 시 null.
     */
    private T content;
}
