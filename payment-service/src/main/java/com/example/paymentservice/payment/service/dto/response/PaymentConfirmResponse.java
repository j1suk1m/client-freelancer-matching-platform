package com.example.paymentservice.payment.service.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PaymentConfirmResponse(
        @JsonProperty("paymentKey") String paymentKey,
        @JsonProperty("orderId") String orderId,
        @JsonProperty("amount") int amount,
        @JsonProperty("status") String status,
        @JsonProperty("approvedAt") String approvedAt,
        @JsonProperty("method") String method
) {}
