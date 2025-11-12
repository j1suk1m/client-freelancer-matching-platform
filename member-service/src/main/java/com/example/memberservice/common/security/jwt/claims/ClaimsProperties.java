package com.example.memberservice.common.security.jwt.claims;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ClaimsProperties {

    @Value("${jwt.claims.member-code}")
    private String memberCodeClaims;

    @Value("${jwt.claims.is-sign}")
    private String isSignClaims;

}
