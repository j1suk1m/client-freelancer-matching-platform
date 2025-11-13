package com.example.cartpostservice.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public record ResponseDto<T>(
        @Schema(description = "성공 및 실패에 대한 구체적인 개별 커스텀 코드", defaultValue = "0")
        int code,

        @JsonProperty("httpStatus")
        @Schema(description = "상태코드", defaultValue = "200")
        int httpStatusCode,

        @Schema(description = "상태 메시지", defaultValue = "이상없음")
        String message,

        @Schema(description = "응답 내용", defaultValue = "XXX")
        T data
) {

}

