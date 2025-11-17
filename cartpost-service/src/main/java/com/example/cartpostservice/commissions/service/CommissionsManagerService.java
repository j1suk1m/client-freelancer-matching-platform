package com.example.cartpostservice.commissions.service;

import com.example.cartpostservice.commissions.controller.dto.request.CommissionCreateRequest;
import com.example.cartpostservice.commissions.controller.dto.response.CommissionCreateResponse;
import com.example.cartpostservice.commissions.controller.dto.response.MemberResponse;
import com.example.cartpostservice.commissions.controller.internal.MemberClient;
import com.example.cartpostservice.commissions.service.dto.request.CommissionsSaveCommand;
import com.example.cartpostservice.commissions.service.dto.request.TagSaveCommand;
import com.example.cartpostservice.commissions.service.dto.response.CommissionCreateResult;
import com.example.cartpostservice.commissions.service.dto.response.CommissionDeleteResult;
import com.example.cartpostservice.commissions.service.dto.response.CommissionFinishResult;
import com.example.cartpostservice.commissions.service.dto.response.CommissionReadResult;
import com.example.cartpostservice.commissions.service.dto.response.CommissionSortReadResult;
import com.example.cartpostservice.commissions.service.dto.response.CommissionUpdateResult;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommissionsManagerService {

    private final CommissionsService commissionsService;
    private final CommissionsTagService commissionsTagService;
    private final MemberClient memberClient;

    @Transactional
    public CommissionCreateResponse createCommission(String memberCode, CommissionCreateRequest request) {

        MemberResponse member = memberClient.getMember(memberCode);

        // request에서 온 것을 커미션과 태그 용 리퀘스트로 분리
        CommissionsSaveCommand commissionsSaveCommand = new CommissionsSaveCommand(
                memberCode,
                request.title(),
                request.content(),
                request.paymentType(),
                request.unitAmount(),
                request.startedAt(),
                request.endedAt(),
                member.nickName()
        );

        // 커미션 서비스에 리퀘스트 데이터를 저장 데이터 받기
        String commissionsCode = commissionsService.create(commissionsSaveCommand);

        // 태그 서비스에 리퀘스트 데이터 저장
        TagSaveCommand tagSaveCommand = new TagSaveCommand(
                commissionsCode,
                request.tagCode()
        );

        commissionsTagService.create(tagSaveCommand);

        // 응답 데이터에 commissionscode 전달
        CommissionCreateResponse commissionCreateResponse = new CommissionCreateResponse(commissionsCode);

        return commissionCreateResponse;
    }

    public CommissionReadResult readCommission(String commissionsCode) {
        return null;
    }

    public CommissionUpdateResult updateCommission(String code, String commissionsCode) {
        return null;
    }

    public CommissionDeleteResult deleteCommission(String code, String commissionsCode) {
        return null;
    }

    public CommissionFinishResult finishCommission(String code) {
        return null;
    }

    public CommissionSortReadResult readOwnCommissions(String code, Pageable pageable) {
        return null;
    }
}
