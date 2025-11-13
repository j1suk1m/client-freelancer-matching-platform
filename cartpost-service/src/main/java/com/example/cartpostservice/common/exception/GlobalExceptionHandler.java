package com.example.cartpostservice.common.exception;

import com.example.cartpostservice.common.dto.EmptyDto;
import com.example.cartpostservice.common.dto.ResponseDto;
import jakarta.validation.Valid;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ResponseDto<EmptyDto>> handleBusinessException(BusinessException ex) {
        log.warn("handleBusinessException: {}", ex.getMessage());

        ErrorCode errorCode = ex.getErrorCode();
        ResponseDto response = ResponseDto.of(errorCode);

        return new ResponseEntity<>(response, errorCode.getStatus());
    }


    //    @ExceptionHandler(MethodArgumentNotValidException.class)
//    protected ResponseEntity<ResponseDto<List<FieldErrorDetail>>> handleMethodArgumentNotValidException(
//            MethodArgumentNotValidException ex) {
//        log.warn("handleMethodArgumentNotValidException: {}", ex.getMessage());
//
//        BindingResult bindingResult = ex.getBindingResult();
//        ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
//
//        // ResponseDto의 오버로딩된 of() 사용
//        ResponseDto<List<FieldErrorDetail>> response = ResponseDto.of(errorCode, bindingResult);
//
//        return new ResponseEntity<>(response, errorCode.getStatus());
//    }
//
//
//    @ExceptionHandler(Exception.class)
//    protected ResponseEntity<ResponseDto<EmptyDto>> handleGeneralException(Exception ex) {
//        log.error("handleGeneralException: {}", ex.getMessage(), ex); // 스택 트레이스 로깅
//
//        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
//        ResponseDto response = ResponseDto.of(errorCode);
//
//        return new ResponseEntity<>(response, errorCode.getStatus());
//    }
}
