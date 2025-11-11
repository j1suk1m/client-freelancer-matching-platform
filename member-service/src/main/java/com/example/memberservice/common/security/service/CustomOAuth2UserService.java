package com.example.memberservice.common.security.service;

import com.example.memberservice.common.redis.service.RedisSingleDataService;
import com.example.memberservice.member.repository.MemberJpaRepository;
import com.example.memberservice.socialmember.repository.SocialMemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final SocialMemberJpaRepository socialMemberRepository;

}
