package com.example.contractservice.contract.service;

import com.example.contractservice.contract.controller.dto.request.ContractCreateRequest;
import com.example.contractservice.contract.controller.dto.response.ContractCreateResponse;
import com.example.contractservice.contract.domain.Contract;
import com.example.contractservice.contract.domain.exception.ContractException;
import com.example.contractservice.contract.domain.exception.ContractErrorCode;
import com.example.contractservice.contract.entity.ContractEntity;
import com.example.contractservice.contract.repository.ContractRepository;
import com.example.contractservice.contract.service.dto.response.MemberInfoResponse;
import com.example.contractservice.contract.service.dto.response.MemberInfoResponse.MemberInfo;
import com.example.contractservice.contract.service.mapper.ContractMapper;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class ContractService {
    private final ContractRepository contractRepository;
    private final RestTemplate restTemplate;

    @Value("${module.member.application.name}")
    private String memberServiceName;

    @Value("${module.member.application.path.member-info}")
    private String memberInfoUrl;

    public ContractCreateResponse requestContract(ContractCreateRequest request) {
        isValidMember(List.of(request.requestorCode(), request.contractorCode()));

        Contract createdContract = request.toContract();

        ContractEntity contractEntity = contractRepository.saveContract(ContractMapper.toEntity(createdContract));
        Contract savedContract = ContractMapper.toDomain(contractEntity);

        return ContractCreateResponse.of(savedContract.getCode());
    }

    private void isValidMember(List<String> memberCodes) {
        URI memberExistsUrl = createMemberInfoUrl(memberCodes);
        MemberInfoResponse memberInfoResponse = Optional.ofNullable(restTemplate.getForObject(memberExistsUrl, MemberInfoResponse.class))
                .orElseThrow(() -> new ContractException(ContractErrorCode.INVALID_MEMBER));

        List<MemberInfo> memberInfos = memberInfoResponse.members();
        
        if (memberInfos.size() != memberCodes.size()) {
            throw new ContractException(ContractErrorCode.INVALID_MEMBER);
        }

        if (noFreelancer(memberInfos)) {
            throw new ContractException(ContractErrorCode.NO_FREELANCERS);
        }
    }

    private boolean noFreelancer(List<MemberInfo> memberInfos) {
        return memberInfos.stream()
                .filter(MemberInfo::canWork)
                .findFirst()
                .isEmpty();
    }

    private URI createMemberInfoUrl(List<String> memberCodes) {
        return UriComponentsBuilder.newInstance()
                .scheme("lb")
                .host(memberServiceName)
                .path(memberInfoUrl)
                .queryParam("member-code", memberCodes)
                .build()
                .toUri();
    }
}
