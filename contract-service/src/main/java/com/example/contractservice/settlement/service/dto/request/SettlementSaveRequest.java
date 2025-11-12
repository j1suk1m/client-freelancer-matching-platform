package com.example.contractservice.settlement.service.dto.request;

import com.example.contractservice.common.PaymentType;
import com.example.contractservice.settlement.domain.Settlement;
import com.example.contractservice.settlement.domain.vo.SettlementReference;
import com.example.contractservice.settlement.domain.vo.SettlementStatusInfo;
import java.time.Instant;

public record SettlementSaveRequest(
        String receiverCode,
        String contractCode,
        Long amount,
        Instant startedAt,
        Instant endedAt,
        PaymentType paymentType
) {

    public Settlement toSettlement() {
        return new Settlement(
                new SettlementReference(receiverCode, contractCode),
                new SettlementStatusInfo(amount, )
        )
    }
}
