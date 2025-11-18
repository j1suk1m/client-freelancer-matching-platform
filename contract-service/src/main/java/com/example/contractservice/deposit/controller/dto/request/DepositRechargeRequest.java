package com.example.contractservice.deposit.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record DepositRechargeRequest(
        @Schema(description = "예치금을 충전하는 멤버 코드", example = "8172516b-2076-460f-805d-e60cbc1463l1")
        String memberCode,
        @Schema(description = "추가할 금액", example = "30000")
        Long amount
) {

}
