package com.example.cartpostservice.commissions.service;

import com.example.cartpostservice.commissions.controller.dto.request.CommissionCreateRequest;
import com.example.cartpostservice.commissions.controller.dto.response.CommissionCreateResponse;
import com.example.cartpostservice.commissions.controller.dto.response.CommissionDeleteResponse;
import com.example.cartpostservice.commissions.controller.dto.response.CommissionFinishResponse;
import com.example.cartpostservice.commissions.controller.dto.response.CommissionReadResponse;
import com.example.cartpostservice.commissions.controller.dto.response.CommissionUpdateResponse;
import com.example.cartpostservice.commissions.controller.dto.response.CommissionsReadResponse;
import com.example.cartpostservice.commissions.controller.dto.response.MemberResponse;
import com.example.cartpostservice.commissions.controller.internal.MemberClient;
import com.example.cartpostservice.commissions.service.dto.request.CommissionsServiceCommand;
import com.example.cartpostservice.commissions.service.dto.request.TagServiceCommand;
import com.example.cartpostservice.commissions.service.dto.response.CommissionsServiceResult;
import com.example.cartpostservice.commissions.service.dto.response.TagServiceResult;
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
        CommissionsServiceCommand commissionsServiceCommand = new CommissionsServiceCommand(
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
        String commissionsCode = commissionsService.create(commissionsServiceCommand);

        // 태그 서비스에 리퀘스트 데이터 저장
        TagServiceCommand tagServiceCommand = new TagServiceCommand(
                commissionsCode,
                request.tagCode()
        );

        commissionsTagService.create(tagServiceCommand);

        // 응답 데이터에 commissionscode 전달
        CommissionCreateResponse commissionCreateResponse = new CommissionCreateResponse(commissionsCode);

        return commissionCreateResponse;
    }

    public CommissionReadResponse readCommission(String commissionsCode) {

        CommissionsServiceResult commissionResult = commissionsService.read(commissionsCode);
        TagServiceResult tagResult = commissionsTagService.read(commissionResult.code());

        CommissionReadResponse response = new CommissionReadResponse(
                commissionResult.title(),
                commissionResult.content(),
                commissionResult.paymentType(),
                commissionResult.unitAmount(),
                commissionResult.startedAt(),
                commissionResult.endedAt(),
                commissionResult.isOpen(),
                commissionResult.writerName(),
                tagResult.tagCodes()
        );

        return response;
    }

    public CommissionUpdateResponse updateCommission(String code, String commissionsCode, CommissionCreateRequest request) {

        MemberResponse member = memberClient.getMember(code);

        CommissionsServiceCommand commissionsServiceCommand = new CommissionsServiceCommand(
                code,
                request.title(),
                request.content(),
                request.paymentType(),
                request.unitAmount(),
                request.startedAt(),
                request.endedAt(),
                member.nickName()
        );

        TagServiceCommand tagServiceCommand = new TagServiceCommand(
                commissionsCode,
                request.tagCode()
        );

        commissionsService.update(commissionsServiceCommand, commissionsCode);
        commissionsTagService.update(tagServiceCommand, commissionsCode);

        return new CommissionUpdateResponse(commissionsCode);
    }

    public void deleteCommission(String code, String commissionsCode) {

        commissionsService.delete(code, commissionsCode);
        commissionsTagService.delete(code, commissionsCode);

    }

    public CommissionFinishResponse finishCommission(String code) {
        return null;
    }

    public CommissionsReadResponse readOwnCommissions(String code, Pageable pageable) {
        return null;
    }
}
