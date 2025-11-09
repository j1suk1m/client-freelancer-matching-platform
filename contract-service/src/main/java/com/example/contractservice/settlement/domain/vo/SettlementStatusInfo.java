package com.example.contractservice.settlement.domain.vo;

import com.example.contractservice.settlement.common.SettlementStatus;
import java.time.Instant;

public record SettlementStatusInfo(
    Long amount,
    SettlementStatus status,
    Instant progressingAt
) {

}
