package com.example.memberservice.common.security.jwt;

import io.jsonwebtoken.Jwts;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenGenerator {

    private final JwtKeyProvider jwtKeyProvider;

    private final JwtProperties jwtProperties;


    public String generateAccessToken(String memberCode, boolean isSign) {
        return Jwts.builder()
            .claim(jwtProperties.getMemberCodeClaims(), memberCode)
            .claim(jwtProperties.getIsSignClaims(), isSign)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getAccessTokenTtl()))
            .signWith(jwtKeyProvider.getAccessTokenSignKey())
            .compact();
    }

    public String generateRefreshToken(String memberCode) {
        return Jwts.builder()
            .claim(jwtProperties.getMemberCodeClaims(), memberCode)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getRefreshTokenTtl()))
            .signWith(jwtKeyProvider.getRefreshTokenSignKey())
            .compact();
    }
}
