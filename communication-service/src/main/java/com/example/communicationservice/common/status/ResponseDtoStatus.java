package com.example.communicationservice.common.status;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseDtoStatus {

    // 요청 성공
    SUCCESS(0, "요청이 성공하였습니다.", HttpStatus.OK),

    // 채팅방 관련 실패
    CHATROOM_INVALID_MEMBER_COUNT(1000, "1:1 채팅은 2명의 참여자가 필요합니다.", HttpStatus.BAD_REQUEST),
    CHATROOM_NOT_INCLUDE_SELF(1001, "현재 로그인한 사용자는 채팅방에 포함되어야 합니다.", HttpStatus.BAD_REQUEST),
    CHATROOM_ALREADY_EXISTS(1002, "이미 채팅방이 존재합니다.", HttpStatus.BAD_REQUEST),

    // 유효성 검사 실패
    VALIDATION_FAILED(40000, "유효하지 않은 입력입니다.", HttpStatus.BAD_REQUEST);

    private final int code;
    private final String message;
    private final int httpStatusCode;

    ResponseDtoStatus(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatus.value();
    }

}
