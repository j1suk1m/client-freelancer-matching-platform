package com.example.gatewayservice.common.exception;

public enum ErrorCode {

    //401
    UNAUTHORIZATION("인증되지 않은 요청입니다.", 401);

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
