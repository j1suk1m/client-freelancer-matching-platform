package com.example.memberservice.common.exception;

public enum ErrorCode {

    //401
    UNAUTHORIZATION(401, "인증되지 않은 요청입니다.", "UNAUTHORIZATION"),

    //404
    MEMBER_NOT_FOUND(404, "요청하신 사용자를 찾을 수 없습니다.", "MEMBER_NOT_FOUND"),

    //500
    DATA_SAVE_FAILED(500, "데이터 저장에 실패했습니다.", "DATA_SAVE_FAILED");

    private final String message;
    private final int statusCode;
    private final String errorCode;

    ErrorCode(int statusCode, String message, String errorCode) {
        this.message = message;
        this.statusCode = statusCode;
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
