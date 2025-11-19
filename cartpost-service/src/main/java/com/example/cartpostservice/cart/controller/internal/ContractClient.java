package com.example.cartpostservice.cart.controller.internal;

import com.example.cartpostservice.cart.controller.dto.request.ContractPayRequest;
import com.example.cartpostservice.cart.controller.dto.response.ContractInfoResponse;
import com.example.cartpostservice.common.dto.ResponseDto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "contract-service") // 대상 서비스 이름 (Eureka 등록명 등)
public interface ContractClient {

    @PostMapping("/internal/contracts/pay")
    ResponseDto<List<ContractInfoResponse>> payContract(
            @RequestHeader("X-CODE") String xCode,
            @RequestBody ContractPayRequest request
    );
}
