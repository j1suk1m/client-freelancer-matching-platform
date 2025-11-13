package com.example.cartpostservice.common.exception;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class FieldErrorDetail {

    private String field;
    private String reason;
    private String value;

    private FieldErrorDetail(FieldError error) {
        this.field = error.getField();
        this.reason = error.getDefaultMessage();
        this.value = error.getRejectedValue() != null ? error.getRejectedValue().toString() : "null";
    }

    public static List<FieldErrorDetail> of(BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        return fieldErrors.stream()
                .map(FieldErrorDetail::new)
                .collect(Collectors.toList());
    }

}
