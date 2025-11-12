package com.example.memberservice.common.security.model.vo;

import java.util.Map;

public class KakaoUserInfo implements OAuthUserInfo {

    private final Map<String, Object> attributes;

    public KakaoUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getProvider() {
        return "KAKAO";
    }

    @Override
    public String getEmail() {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Object objectEmail = kakaoAccount.get("email");

        //카카오는 이메일을 받아오기 위해 프론트 화면 생성 이후 검수를 받아야함.
        if (objectEmail == null) {
            throw new IllegalArgumentException("Email not found in attributes");
        }

        return objectEmail.toString();
    }
}
