package com.example.communicationservice.common.status;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseDtoStatus {

    // 요청 성공
    SUCCESS(0, "요청이 성공하였습니다.", HttpStatus.OK);

    private final int code;
    private final String message;
    private final int httpStatusCode;

    ResponseDtoStatus(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatus.value();
    }

}
