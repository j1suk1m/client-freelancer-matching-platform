package com.example.profileservice.common.model.vo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 비즈니스 로직에서 발생하는 커스텀 예외 클래스
 */
@Getter
public class CustomException extends RuntimeException {

    private final HttpStatus status;
    private final String message;

    public CustomException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
