package com.example.memberservice.common.exception;

public enum ErrorCode {

    //401
    UNAUTHORIZATION("인증되지 않은 요청입니다.", 401),

    //404
    MEMBER_NOT_FOUND("요청하신 사용자를 찾을 수 없습니다.",404);

    private final String message;
    private final int statusCode;

    ErrorCode(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
