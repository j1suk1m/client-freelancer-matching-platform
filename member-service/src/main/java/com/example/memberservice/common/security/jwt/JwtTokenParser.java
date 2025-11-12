package com.example.memberservice.common.security.jwt;

import com.example.memberservice.common.exception.BusinessException;
import com.example.memberservice.common.exception.ErrorCode;
import com.example.memberservice.common.security.jwt.claims.ClaimsProperties;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenParser {

    private final ClaimsProperties claimsProperties;

    public String parseMemberCode(Claims claims) {
        Object memberCode = claims.get(claimsProperties.getMemberCodeClaims());
        if (memberCode == null) {
            throw new BusinessException(ErrorCode.UNAUTHORIZATION);
        }
        return memberCode.toString();
    }

}
