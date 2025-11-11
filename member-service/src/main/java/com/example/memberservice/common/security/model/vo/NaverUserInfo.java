package com.example.memberservice.common.security.model.vo;

import java.util.Map;

public class NaverUserInfo implements OAuthUserInfo {

    private final Map<String, Object> attributes;

    public NaverUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return response.get("id").toString();
    }

    @Override
    public String getProvider() {
        return "NAVER";
    }

    @Override
    public String getEmail() {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        String email = response.get("email").toString();
        if (email == null) {
            throw new IllegalArgumentException("Email not found in attributes");
        }
        return email;
    }
}
