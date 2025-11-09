package com.example.contractservice.controller.dto.request;

import java.time.Instant;

public record ContractCreateRequest(
        String freelancerId,
        String clientId,
        Instant startedAt,
        Instant endedAt,
        String paymentType,
        Long unitAmount,
        String name,
        String body
) {}
