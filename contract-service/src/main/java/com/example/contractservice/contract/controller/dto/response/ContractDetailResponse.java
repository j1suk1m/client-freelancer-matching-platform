package com.example.contractservice.contract.controller.dto.response;

import java.time.Instant;

public record ContractDetailResponse(
    String clientName,
    String freelancerName,
    Instant createdAt,
    Instant startedAt,
    Instant endedAt,
    String paymentType,
    String status,
    String name,
    String body
) {

}
