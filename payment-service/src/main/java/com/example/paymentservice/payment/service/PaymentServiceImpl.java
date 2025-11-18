package com.example.paymentservice.payment.service;

import com.example.paymentservice.payment.model.PaymentEntity;
import com.example.paymentservice.payment.model.PaymentStatus;
import com.example.paymentservice.payment.repository.OrderRepository;
import com.example.paymentservice.payment.repository.PaymentRepository;
import com.example.paymentservice.payment.service.dto.response.PayRechargeResult;
import com.example.paymentservice.payment.service.dto.response.PaymentConfirmResponse;
import com.example.paymentservice.payment.service.dto.response.PaymentGetResult;
import com.example.paymentservice.payment.service.dto.response.PaymentsGetResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final ObjectMapper om = new ObjectMapper();

    @Transactional
    public void confirmAndSave(InputStream tossResponseStream) throws Exception {
        InputStreamReader reader = new InputStreamReader(tossResponseStream, StandardCharsets.UTF_8);
        PaymentConfirmResponse paymentResponse = om.readValue(reader, PaymentConfirmResponse.class);

        PaymentEntity payment = PaymentEntity.builder()
                .paymentKey(paymentResponse.paymentKey())
                .orderPgId(paymentResponse.orderId())
                .amount((long)paymentResponse.amount())
                .paymentStatus(PaymentStatus.valueOf(paymentResponse.status()))
                .method(paymentResponse.method())
                .approveAt(LocalDateTime.parse(paymentResponse.approvedAt()))
                .build();

        paymentRepository.save(payment);
    }

    @Override
    public PayRechargeResult payForRecharge(String code, String amount) {
        return null;
    }

    @Override
    public PaymentsGetResult getAllPayments(String code) {
        return null;
    }

    @Override
    public PaymentGetResult getPaymentByOrderCode(String code, String orderPgId) {
        return null;
    }
}
