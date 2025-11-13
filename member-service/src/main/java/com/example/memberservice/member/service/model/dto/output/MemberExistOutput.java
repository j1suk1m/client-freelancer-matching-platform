package com.example.memberservice.member.service.model.dto.output;

import java.util.List;

public record MemberExistOutput(
    List<String> exists,
    List<String> notExists
) {

    //혹시나 잘못 사용할 때를 대비해서 추가한 안전장치
    public MemberExistOutput {
        if (exists == null) {
            exists = List.of();
        }
        if (notExists == null) {
            notExists = List.of();
        }
    }
}
