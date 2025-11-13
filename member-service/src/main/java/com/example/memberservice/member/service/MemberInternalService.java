package com.example.memberservice.member.service;

import com.example.memberservice.member.entity.Members;
import com.example.memberservice.member.repository.MemberJpaRepository;
import com.example.memberservice.member.service.model.dto.output.MemberExistOutput;
import com.example.memberservice.member.service.model.dto.output.MemberInfoOutput;
import com.example.memberservice.member.service.model.vo.MemberInfo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberInternalService {

    private final MemberJpaRepository memberJpaRepository;

    public List<MemberInfoOutput> getMemberInfos(List<String> memberCodes) {
        List<Members> findMembers = memberJpaRepository.findAllByCode(memberCodes);

        Set<String> setMemberCodes = new HashSet<>(memberCodes);

        if (setMemberCodes.size() > findMembers.size()) {
            //TODO BusinnessException 추가 이후
            throw new IllegalArgumentException("요청하신 memberCode 중 없는 memberCode가 존재합니다.");
        }

        return List.of(
            new MemberInfoOutput(
                findMembers.stream()
                    .map(m -> new MemberInfo(
                        m.getCode(),
                        m.getName(),
                        m.getCanWork()
                    ))
                    .toList()
            )
        );
    }

    public List<MemberExistOutput> getMemberExists(List<String> memberCodes) {
        List<Members> findMembers = memberJpaRepository.findAllByCode(memberCodes);

        List<String> exists = new ArrayList<>();

        List<String> notExists = new ArrayList<>();

        return null;
    }
}
