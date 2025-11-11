package com.example.memberservice.common.security.handler;

import com.example.memberservice.common.redis.service.RedisSingleDataService;
import com.example.memberservice.common.security.jwt.JwtTokenGenerator;
import com.example.memberservice.common.security.jwt.JwtTokenValidator;
import com.example.memberservice.common.security.model.dto.CustomOAuth2UserDto;
import com.example.memberservice.common.security.service.CustomOAuth2UserService;
import com.example.memberservice.common.web.CookieGenerator;
import com.example.memberservice.member.repository.MemberJpaRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuthLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final RedisSingleDataService redisSingleDataService;

    private final JwtTokenGenerator tokenGenerator;

    private final MemberJpaRepository memberJpaRepository;

    private final OAuthLoginFailureHandler oAuthLoginFailureHandler;

    @Value("${jwt.refresh-token.ttl}")
    private Long refreshTokenTtl;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {

        CustomOAuth2UserDto oAuth2User = (CustomOAuth2UserDto) authentication.getPrincipal();

        String memberCode = oAuth2User.getMemberCode();

        String refreshToken = tokenGenerator.generateRefreshToken();

        //레디스에 refreshToken - member code 형태로 저장. ttl 은 14일
        try{
            if(0==redisSingleDataService.setSingleData(refreshToken, memberCode, refreshTokenTtl)){
                //TODO redis refeshToken 저장에 실패했다.
                throw new IOException();
            }
        }catch (IOException e){
            oAuthLoginFailureHandler.onAuthenticationFailure(request, response,
                new AuthenticationServiceException("Redis가 불안정합니다.", e));

            return;
        }

        String redirectUri;

        //소셜로그인에 회원가입까지 완료했다면
        if(memberJpaRepository.existsByCode(memberCode)){
            redirectUri = "http://localhost:8000/api/members/healthCheck";
        }else{
            redirectUri = "http://localhost:8000/api/members/connectCheck";
        }

        response.addHeader(HttpHeaders.SET_COOKIE, CookieGenerator.createCookies("RefreshToken", refreshToken, TimeUnit.MILLISECONDS.toSeconds(refreshTokenTtl)));
        response.sendRedirect(redirectUri);
    }
}
