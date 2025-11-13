package com.example.cartpostservice.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    SUCCESS(HttpStatus.OK, 0, "성공");


    private final HttpStatus status;
    private final int code;
    private final String message;
}
