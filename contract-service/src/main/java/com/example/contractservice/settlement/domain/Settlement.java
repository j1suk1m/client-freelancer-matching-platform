package com.example.contractservice.settlement.domain;

import com.example.contractservice.settlement.domain.vo.SettlementReference;
import com.example.contractservice.settlement.domain.vo.SettlementStatusInfo;
import java.time.Instant;
import java.util.UUID;

public class Settlement {

    private String code;

    private SettlementReference settlementReference;

    private SettlementStatusInfo settlementStatusInfo;

    private Instant createdAt;
    private Instant processedAt;

    public Settlement(String code, SettlementReference settlementReference,
        SettlementStatusInfo settlementStatusInfo, Instant createdAt, Instant processedAt) {
        this.code = (code == null) ? generateCode() : code;
        this.settlementReference = settlementReference;
        this.settlementStatusInfo = settlementStatusInfo;
        this.createdAt = (createdAt == null) ? Instant.now() : createdAt;
        this.processedAt = processedAt;
    }

    private String generateCode() {
        return UUID.randomUUID().toString();
    }
}
