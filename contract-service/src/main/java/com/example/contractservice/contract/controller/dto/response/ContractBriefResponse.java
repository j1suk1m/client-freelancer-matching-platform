package com.example.contractservice.contract.controller.dto.response;

import java.time.Instant;

public record ContractBriefResponse(
    String clientName,
    String freelancerName,
    Instant startedAt,
    Instant endedAt,
    String status,
    String name
) {

}
