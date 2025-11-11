package com.example.memberservice.common.security.jwt;


import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenValidator {

    private final JwtKeyProvider jwtKeyProvider;

    //AccessToken의 검증은 Api Gateway에서 일어나기 떄문에 Member 모듈에서는 AccessToken 검증은 생략
    public void validateRefreshTokenToken(String token) throws Exception {
        try {
            Jwts.parserBuilder()
                .setSigningKey(jwtKeyProvider.getRefreshTokenSignKey())
                .build()
                .parseClaimsJws(token);
        } catch (JwtException e) {
            //TODO JwtToken 관련 비지니스 Exception으로 변경
            throw new Exception();
        }
    }
}
