package com.example.memberservice.oauth.service;

import com.example.memberservice.common.exception.BusinessException;
import com.example.memberservice.common.exception.ErrorCode;
import com.example.memberservice.common.redis.service.RedisSingleDataService;
import com.example.memberservice.common.security.jwt.JwtTokenGenerator;
import com.example.memberservice.common.security.jwt.JwtTokenParser;
import com.example.memberservice.common.security.jwt.JwtTokenValidator;
import com.example.memberservice.member.repository.MemberJpaRepository;
import com.example.memberservice.oauth.service.dto.output.TokensOutput;
import io.jsonwebtoken.Claims;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final RedisSingleDataService redisSingleDataService;

    private final MemberJpaRepository memberJpaRepository;

    private final JwtTokenValidator jwtTokenValidator;

    private final JwtTokenGenerator jwtTokenGenerator;

    private final JwtTokenParser jwtTokenParser;

    public TokensOutput reissueAccessTokenByRefreshToken(String refreshToken) {
        Claims claims = jwtTokenValidator.validateRefreshTokenToken(refreshToken);

        String memberCode = jwtTokenParser.parseMemberCode(claims);

        Optional<String> optionalExistRefreshToken = redisSingleDataService.getSingleData(memberCode);

        String existRefreshToken = optionalExistRefreshToken.orElseThrow(() -> new BusinessException(ErrorCode.UNAUTHORIZATION));

        if(!existRefreshToken.equals(refreshToken)){
            throw new BusinessException(ErrorCode.UNAUTHORIZATION);
        }

        String newRefreshToken = jwtTokenGenerator.generateRefreshToken(memberCode);

        boolean isSign = memberJpaRepository.existsByCode(memberCode);

        String newAccessToken = jwtTokenGenerator.generateAccessToken(memberCode,isSign);

        return new TokensOutput(newRefreshToken, newAccessToken);

    }

    public void deleteRefreshTokenToRedis(String refreshToken){

    }
}
