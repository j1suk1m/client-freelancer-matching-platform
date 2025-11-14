package com.example.cartpostservice.commissions.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentType {
    MONTH("월급"), PER_JOB("건당");

    private final String description;
}
