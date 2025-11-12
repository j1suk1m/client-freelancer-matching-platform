package com.example.memberservice.common.security.jwt;

import com.example.memberservice.common.security.jwt.claims.ClaimsProperties;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenGenerator {

    private final JwtKeyProvider jwtKeyProvider;

    private final ClaimsProperties claimsProperties;

    @Value("${jwt.access-token.ttl}")
    private long accessTokenTtl;

    @Value("${jwt.refresh-token.ttl}")
    private long refreshTokenTtl;



    public String generateAccessToken(String memberCode, boolean isSign) {
        return Jwts.builder()
            .claim(claimsProperties.getMemberCodeClaims(), memberCode)
            .claim(claimsProperties.getIsSignClaims(),isSign)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + accessTokenTtl))
            .signWith(jwtKeyProvider.getAccessTokenSignKey())
            .compact();
    }

    public String generateRefreshToken(String memberCode) {
        return Jwts.builder()
            .claim(claimsProperties.getMemberCodeClaims(), memberCode)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + refreshTokenTtl))
            .signWith(jwtKeyProvider.getRefreshTokenSignKey())
            .compact();
    }
}
