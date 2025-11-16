package com.example.memberservice.member.service;

import com.example.memberservice.member.controller.dto.response.UserGetResponse;
import com.example.memberservice.member.service.model.dto.input.MemberCreateInput;
import com.example.memberservice.member.service.model.dto.input.MemberDeleteInput;
import com.example.memberservice.member.service.model.dto.input.MemberExistByNameInput;
import com.example.memberservice.member.service.model.dto.input.MemberGetInput;
import com.example.memberservice.member.service.model.dto.input.MemberUpdateInput;
import com.example.memberservice.member.service.model.dto.input.MemberUpdateWorkStateInput;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

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
