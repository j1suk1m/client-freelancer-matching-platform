package com.example.profileservice.common.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"success", "code", "httpStatus", "message", "result"})
public class BaseResponse<T> {

    private final boolean success;

    private final int code;

    @JsonProperty("httpStatus")
    private final int httpStatusCode;

    private final String message;

    private final T result;

    // 요청에 성공한 경우 - 결과 값이 없을 때
    public static BaseResponse<Void> success() {
        return new BaseResponse<>(BaseResponseStatus.SUCCESS, null);
    }

    // 요청에 성공한 경우 - 결과 값이 있을 때
    public static <T> BaseResponse<T> success(T result) {
        return new BaseResponse<>(BaseResponseStatus.SUCCESS, result);
    }

    // 요청 실패 시 BaseResponse를 생성 (GlobalExceptionHandler에서 사용)
    public static BaseResponse<Void> fail(String code, String message) {
        // BaseResponse의 code(int)와 ErrorCode(String) 타입 불일치 해결을 위한 임시 변환 로직
        int defaultCode = 9999;

        try {
            // "T101"에서 숫자 부분만 추출하여 int로 변환 시도
            defaultCode = Integer.parseInt(code.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException ignored) {
            // 변환 실패 시 기본값 사용
        }

        return new BaseResponse<>(
                false,
                defaultCode,
                HttpStatus.BAD_REQUEST.value(),
                message,
                null
        );
    }

    // 요청에 실패한 경우 - 결과 값이 없을 때
    public static BaseResponse<Empty> error(BaseResponseStatus status) {
        return new BaseResponse<>(status, Empty.getInstance());
    }

    // 요청에 실패한 경우 - 추가 메시지 또는 데이터가 있는 경우 (ErrorResponse 사용 권장)
    public static <T> BaseResponse<T> error(BaseResponseStatus status, T result) {
        return new BaseResponse<>(status, result);
    }

    private BaseResponse(BaseResponseStatus status, T result) {
        this.success = status.isSuccess();
        this.code = status.getCode();
        this.httpStatusCode = status.getHttpStatusCode();
        this.message = status.getMessage();
        this.result = result;
    }
}
