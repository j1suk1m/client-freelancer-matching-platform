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
        String email = null;//= kakaoAccount.get("email").toString();

        //카카오는 이메일을 받아오기 위해 프론트 화면 생성 이후 검수를 받아야함.
        if (email == null) {
            email = "noEmail@email.com";
        }

        return email;
    }
}
