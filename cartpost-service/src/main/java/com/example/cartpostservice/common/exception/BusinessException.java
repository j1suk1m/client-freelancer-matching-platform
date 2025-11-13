package com.example.cartpostservice.common.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage()); // RuntimeException의 message 필드에 메시지를 설정합니다.
        this.errorCode = errorCode;
    }
}
