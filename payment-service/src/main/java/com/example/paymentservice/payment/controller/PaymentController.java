package com.example.paymentservice.payment.controller;

import com.example.paymentservice.common.dto.ResponseDto;
import com.example.paymentservice.payment.controller.dto.response.PayRechargeResponse;
import com.example.paymentservice.payment.controller.dto.response.PaymentGetResponse;
import com.example.paymentservice.payment.controller.dto.response.PaymentsGetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import jakarta.servlet.http.HttpServletRequest;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
// @RestController로 변경하여 JSON 응답을 쉽게 처리합니다.
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@RestController
@RequestMapping("/api/payments")
public class PaymentController implements PaymentApi {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/confirm")
    public ResponseEntity<JSONObject> confirmPayment(@RequestBody String jsonBody) throws Exception {

        JSONParser parser = new JSONParser();
        String orderId;
        String amount; // V1 예제에서는 amount가 String이었습니다.
        String paymentKey;
        try {
            // 클라이언트에서 받은 JSON 요청 바디입니다.
            JSONObject requestData = (JSONObject) parser.parse(jsonBody);
            paymentKey = (String) requestData.get("paymentKey");
            orderId = (String) requestData.get("orderId");
            amount = (String) requestData.get("amount");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        ;
        JSONObject obj = new JSONObject();
        obj.put("orderId", orderId);
        obj.put("amount", Long.parseLong(amount)); // 토스 API는 amount를 Number 타입으로 받습니다.
        obj.put("paymentKey", paymentKey);

        // V1 (결제 위젯) 테스트 시크릿 키입니다. (V2와 다름)
        // 참고: 이 키는 V1 문서 예제용 키(test_gsk_...)입니다.
        // 실제 V1 연동 시에는 '내 개발 정보'의 '시크릿 키'(test_sk_...)를 사용해야 합니다.
        // 여기서는 공식 예제 코드 그대로 test_gsk_... 키를 사용합니다.
        String widgetSecretKey = "test_gsk_docs_OaPz8L5KdmQXkzRz3y47BMw6";
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode((widgetSecretKey + ":").getBytes(StandardCharsets.UTF_8));
        String authorizations = "Basic " + new String(encodedBytes);

        // 결제를 승인하면 결제수단에서 금액이 차감돼요.
        URL url = new URL("https://api.tosspayments.com/v1/payments/confirm");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", authorizations);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(obj.toString().getBytes("UTF-8"));

        int code = connection.getResponseCode();
        boolean isSuccess = code == 200;

        InputStream responseStream = isSuccess ? connection.getInputStream() : connection.getErrorStream();

        // 결제 성공 및 실패 비즈니스 로직을 구현하세요.
        Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        responseStream.close();

        return ResponseEntity.status(code).body(jsonObject);
    }


    @Override
    @PostMapping("/charge")
    public ResponseEntity<ResponseDto<PayRechargeResponse>> payForRecharge(@RequestHeader(name = "X-CODE") String code,
            @PathVariable String amount) {
        return null;
    }

    @Override
    @GetMapping("/orders")
    public ResponseEntity<ResponseDto<PaymentsGetResponse>> getAllPayments(
            @RequestHeader(name = "X-CODE") String code) {
        return null;
    }

    @Override
    @GetMapping("/orders/{order-pg-id}")
    public ResponseEntity<ResponseDto<PaymentGetResponse>> getPaymentByOrderCode(
            @RequestHeader(name = "X-CODE") String code, @PathVariable("order-pg-id") String orderPgId) {
        return null;
    }
}
