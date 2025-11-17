package com.example.cartpostservice.commissions.service;

import com.example.cartpostservice.commissions.model.CommissionsEntity;
import com.example.cartpostservice.commissions.repository.CommissionsRepository;
import com.example.cartpostservice.commissions.service.dto.request.CommissionsSaveCommand;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommissionsService implements CrudService<CommissionsSaveCommand, String> {

    private final CommissionsRepository commissionsRepository;

    @Override
    public String create(CommissionsSaveCommand requestDto) {
        CommissionsEntity commissions = CommissionsEntity.builder()
                .memberCode(requestDto.memberCode())
                .title(requestDto.title())
                .content(requestDto.content())
                .paymentType(requestDto.paymentType())
                .unitAmount(requestDto.unitAmount())
                .startedAt(requestDto.startedAt())
                .endedAt(requestDto.endedAt())
                .build();

        CommissionsEntity saved = commissionsRepository.save(commissions);

        return saved.getCode();
    }

    @Override
    public Optional<CommissionsSaveCommand> read(String commissionsCode) {
        return Optional.empty();
    }

    @Override
    public void update(CommissionsSaveCommand requestDto, String commissionsCode) {

    }

    @Override
    public void delete(String commissionsCode) {

    }

    @Override
    public boolean exist(String ownerCode, String targetCode) {
        return false;
    }
}