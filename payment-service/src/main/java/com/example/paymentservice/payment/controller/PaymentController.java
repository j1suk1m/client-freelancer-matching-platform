package com.example.paymentservice.payment.controller;

import com.example.paymentservice.common.dto.ResponseDto;
import com.example.paymentservice.payment.controller.dto.response.PayRechargeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController implements PaymentApi {

    @Override
    public ResponseEntity<ResponseDto<PayRechargeResponse>> payForRecharge(String code, String amount) {
        return null;
    }
}
