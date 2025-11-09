package com.example.contractservice.contract.controller.dto.response;

import java.time.Instant;

public record ContractCreateResponse(
    String code,
    Instant startedAt,
    Instant endedAt,
    long unitAmount,
    String name,
    String body
) {

}
