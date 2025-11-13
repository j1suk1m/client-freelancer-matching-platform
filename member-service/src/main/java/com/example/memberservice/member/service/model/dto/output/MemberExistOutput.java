package com.example.memberservice.member.service.model.dto.output;

import java.util.List;

public record MemberExistOutput(
    List<String> exist,
    List<String> notExist
) {

    //혹시나 잘못 사용할 때를 대비해서 추가한 안전장치
    public MemberExistOutput {
        if (exist == null) {
            exist = List.of();
        }
        if (notExist == null) {
            notExist = List.of();
        }
    }
}
