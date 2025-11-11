package com.example.contractservice.contract.controller.dto.request;

import java.util.List;

public record ContractPayRequest(
        List<String> codes
) {

}
