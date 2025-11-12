package com.example.gatewayservice.common.jwt;

import com.example.gatewayservice.common.exception.BusinessException;
import com.example.gatewayservice.common.exception.ErrorCode;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenParser {

    private final JwtProperties jwtProperties;

    public String parseMemberCode(Claims claims) {
        Object memberCode = claims.get(jwtProperties.getMemberCodeClaims());
        if (memberCode == null) {
            throw new BusinessException(ErrorCode.UNAUTHORIZATION);
        }
        return memberCode.toString();
    }
}
