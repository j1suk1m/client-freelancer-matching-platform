package com.example.contractservice.deposit.controller.dto.response;

import java.time.Instant;

public record DepositHistoryInfoResponse(
    Instant createdAt,
    Long changeAmount,
    Long resultAmount,
    String summary
) {

}
