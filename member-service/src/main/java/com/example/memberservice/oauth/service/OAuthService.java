package com.example.memberservice.oauth.service;

import com.example.memberservice.common.redis.service.RedisSingleDataService;
import com.example.memberservice.common.security.jwt.JwtTokenGenerator;
import com.example.memberservice.common.security.jwt.JwtTokenValidator;
import com.example.memberservice.member.repository.MemberJpaRepository;
import com.example.memberservice.socialmember.repository.SocialMemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final RedisSingleDataService redisSingleDataService;

    private final MemberJpaRepository memberJpaRepository;

    private final SocialMemberJpaRepository socialMemberJpaRepository;

    private final JwtTokenValidator jwtTokenValidator;

    private final JwtTokenGenerator jwtTokenGenerator;

    public void reissueAccessTokenByRefreshToken(String refreshToken) {

    }

    public void deleteRefreshTokenToRedis(String refreshToken){

    }
}
