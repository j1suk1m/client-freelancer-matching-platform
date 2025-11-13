package com.example.memberservice.member.service;

import com.example.memberservice.member.entity.Members;
import com.example.memberservice.member.repository.MemberJpaRepository;
import com.example.memberservice.member.service.model.dto.output.MemberExistOutput;
import com.example.memberservice.member.service.model.dto.output.MemberInfoOutput;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberInternalService {

    private final MemberJpaRepository memberJpaRepository;

    public List<MemberInfoOutput> getMemberInfos(List<String> memberCodes){
        List<Members> findMembers = memberJpaRepository.findAllByCode(memberCodes);

        return null;
    }

    public List<MemberExistOutput> getMemberExists(List<String> memberCodes){
        List<Members> findMembers = memberJpaRepository.findAllByCode(memberCodes);

        List<String> exists = new ArrayList<>();

        List<String> notExists = new ArrayList<>();



        return null;
    }
}
