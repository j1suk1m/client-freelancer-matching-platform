package com.example.memberservice.member.service;

import com.example.memberservice.member.controller.dto.response.UserGetResponse;
import com.example.memberservice.member.service.model.dto.input.MemberCreateInput;
import com.example.memberservice.member.service.model.dto.input.MemberDeleteInput;
import com.example.memberservice.member.service.model.dto.input.MemberExistByNameInput;
import com.example.memberservice.member.service.model.dto.input.MemberGetInput;
import com.example.memberservice.member.service.model.dto.input.MemberUpdateInput;
import com.example.memberservice.member.service.model.dto.input.MemberUpdateWorkStateInput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    // 멤버 조회에서 태그 정보와 평가 정보를 받아올 RestTemplate
    private final RestTemplate restTemplate;

    private final

    @Override
    public UserGetResponse getMemberByCode(MemberGetInput input) {
        return null;
    }

    @Override
    public void createMember(MemberCreateInput input) {

    }

    @Override
    public void updateMember(MemberUpdateInput input) {

    }

    @Override
    public void updateMemberWorkState(MemberUpdateWorkStateInput input) {

    }

    @Override
    public void deleteMember(MemberDeleteInput input) {

    }

    @Override
    public void existMemberByName(MemberExistByNameInput input) {

    }
}
