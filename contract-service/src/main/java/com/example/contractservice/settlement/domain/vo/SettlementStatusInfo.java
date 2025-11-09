package com.example.contractservice.settlement.domain.vo;

import java.time.Instant;

public record SettlementStatusInfo(
    Long amount,
    String status,
    Instant progressingAt
) {

}
