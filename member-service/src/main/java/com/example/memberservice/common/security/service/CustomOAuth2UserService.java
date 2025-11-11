package com.example.memberservice.common.security.service;

import com.example.memberservice.common.model.vo.Provider;
import com.example.memberservice.common.security.model.dto.CustomOAuth2UserDto;
import com.example.memberservice.common.security.model.dto.UserInfoDto;
import com.example.memberservice.common.security.model.vo.GoogleUserInfo;
import com.example.memberservice.common.security.model.vo.KakaoUserInfo;
import com.example.memberservice.common.security.model.vo.NaverUserInfo;
import com.example.memberservice.common.security.model.vo.OAuthUserInfo;
import com.example.memberservice.socialmember.entity.SocialMembers;
import com.example.memberservice.socialmember.repository.SocialMemberJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final SocialMemberJpaRepository socialMemberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getClientName();

        OAuthUserInfo oAuthUserInfo = getOAuthUserInfo(provider, oAuth2User);

        String providerId = oAuthUserInfo.getProviderId();

        String memberEmail = oAuthUserInfo.getEmail();

        SocialMembers existMember = socialMemberRepository.findSocialMembersByProviderId(providerId).orElse(null);

        SocialMembers member;

        member = loadOrCreateSocialMember(existMember, provider, memberEmail, providerId);

        return new CustomOAuth2UserDto(oAuth2User, new UserInfoDto(member.getCode(), member.getProviderId(), member.getProvider()));
    }

    private SocialMembers loadOrCreateSocialMember(SocialMembers existMember, String provider, String memberEmail,
        String providerId) {
        SocialMembers member;
        //신규 가입의 경우
        if (existMember == null) {
            log.info("신규 회원입니다. 회원에 대한 정보를 저장합니다.");

            member = SocialMembers.builder()
                .provider(Provider.valueOf(provider.toUpperCase()))
                .email(memberEmail)
                .providerId(providerId)
                .build();

            member = socialMemberRepository.save(member);
            //이미 가입했던 유저의 경우
        } else {
            log.info("기존 유저입니다.");
            member = existMember;
        }

        return member;
    }

    private OAuthUserInfo getOAuthUserInfo(String provider, OAuth2User oAuth2User)
        throws OAuth2AuthenticationException {

        if (provider.equals("KAKAO")) {
            return new KakaoUserInfo(oAuth2User.getAttributes());
        } else if (provider.equals("GOOGLE")) {
            return new GoogleUserInfo(oAuth2User.getAttributes());
        } else if (provider.equals("NAVER")) {
            return new NaverUserInfo(oAuth2User.getAttributes());
        }

        throw new OAuth2AuthenticationException("사용자 정보 불러오기에 실패했습니다.");
    }
}
