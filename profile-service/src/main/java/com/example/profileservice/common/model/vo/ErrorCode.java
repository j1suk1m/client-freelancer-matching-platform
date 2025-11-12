package com.example.profileservice.common.model.vo;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // 4xx 클라이언트 에러 - 일반
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C001", "입력 값이 유효하지 않습니다."),
    NOT_FOUND_RESOURCE(HttpStatus.NOT_FOUND, "C002", "요청하신 리소스를 찾을 수 없습니다."),
    UNAUTHORIZED_ACCESS(HttpStatus.FORBIDDEN, "C003", "접근 권한이 없습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "C004", "지원되지 않는 HTTP 메서드입니다."),
    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST, "C005", "유효하지 않은 타입의 값입니다."),
    INVALID_MEMBER_CODE(HttpStatus.BAD_REQUEST, "C006", "유효하지 않은 사용자 코드입니다."),

    // 4xx 클라이언트 에러 - experience

    // 4xx 클라이언트 에러 - rating


    // 4xx 클라이언트 에러 - resume


    // 4xx 클라이언트 에러 - selfPromotion

    // 4xx 클라이언트 에러 - Tag
    TAG_NOT_FOUND(HttpStatus.NOT_FOUND, "T101", "해당 기술 태그를 찾을 수 없습니다."),
    TAG_ALREADY_EXISTS(HttpStatus.CONFLICT, "T102", "이미 존재하는 기술 태그입니다."),
    MEMBER_TAG_ALREADY_CONNECTED(HttpStatus.CONFLICT, "T103", "이미 연결된 기술 태그입니다."),
    MEMBER_TAG_NOT_FOUND(HttpStatus.NOT_FOUND, "T104", "해제할 기술 태그 연결을 찾을 수 없습니다."),

    // 500 서버 에러
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "S001", "서버 내부 오류가 발생했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(final HttpStatus status, final String code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
