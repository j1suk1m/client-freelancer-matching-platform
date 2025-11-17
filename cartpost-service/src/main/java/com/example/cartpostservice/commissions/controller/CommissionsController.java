package com.example.cartpostservice.commissions.controller;

import com.example.cartpostservice.commissions.controller.dto.request.CommissionCreateRequest;
import com.example.cartpostservice.commissions.controller.dto.response.CommissionCreateResponse;
import com.example.cartpostservice.commissions.controller.dto.response.CommissionDeleteResponse;
import com.example.cartpostservice.commissions.controller.dto.response.CommissionFinishResponse;
import com.example.cartpostservice.commissions.controller.dto.response.CommissionReadResponse;
import com.example.cartpostservice.commissions.controller.dto.response.CommissionUpdateResponse;
import com.example.cartpostservice.commissions.controller.dto.response.CommissionsReadResponse;
import com.example.cartpostservice.commissions.service.CommissionsManagerService;
import com.example.cartpostservice.commissions.service.CommissionsService;
import com.example.cartpostservice.common.dto.ResponseDto;
import com.example.cartpostservice.common.exception.CustomStatusCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/commissions")
@RequiredArgsConstructor
public class CommissionsController implements CommissionsApi {

    private final CommissionsManagerService commissionsManagerService;

    @Override
    @PostMapping
    public ResponseEntity<ResponseDto<CommissionCreateResponse>> createCommission(@RequestHeader("X-CODE") String code,
            @Valid @RequestBody CommissionCreateRequest commissionCreateRequest) {

        CommissionCreateResponse response = commissionsManagerService.createCommission(code, commissionCreateRequest);

        return new ResponseEntity<>(ResponseDto.success(CustomStatusCode.CREATED, response),
                CustomStatusCode.CREATED.getStatus());
    }

    @Override
    @GetMapping("/{commission-code}")
    public ResponseEntity<ResponseDto<CommissionReadResponse>> readCommission(@PathVariable(name = "commission-code") String commissionsCode) {

        CommissionReadResponse response = commissionsManagerService.readCommission(commissionsCode);

        return new ResponseEntity<>(ResponseDto.success(CustomStatusCode.SUCCESS, response),
                CustomStatusCode.SUCCESS.getStatus());
    }

    @Override
    @PatchMapping("/{commission-code}")
    public ResponseEntity<ResponseDto<CommissionUpdateResponse>> updateCommission(
            @RequestHeader("X-CODE") String code,
            @PathVariable String commissionsCode
    ) {
        return null;
    }

    @Override
    @DeleteMapping("/{commission-code}")
    public ResponseEntity<ResponseDto<CommissionDeleteResponse>> deleteCommission(
            @RequestHeader("X-CODE") String code,
            @PathVariable String commissionsCode) {
        return null;
    }

    @Override
    @PatchMapping("/deadline")
    public ResponseEntity<ResponseDto<CommissionFinishResponse>> finishCommission(
            @RequestHeader("X-CODE") String code) {
        return null;
    }

    @Override
    @GetMapping("/total")
    public ResponseEntity<ResponseDto<CommissionsReadResponse>> readOwnCommissions(
            @RequestHeader("X-CODE") String code,
            Pageable pageable) {
        return null;
    }
}
