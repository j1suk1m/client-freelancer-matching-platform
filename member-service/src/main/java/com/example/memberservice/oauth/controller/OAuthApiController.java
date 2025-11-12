package com.example.memberservice.oauth.controller;

import com.example.memberservice.common.exception.BusinessException;
import com.example.memberservice.common.exception.ErrorCode;
import com.example.memberservice.common.model.vo.ResponseDto;
import com.example.memberservice.common.security.jwt.JwtProperties;
import com.example.memberservice.common.web.CookieGenerator;
import com.example.memberservice.oauth.controller.swagger.OAuthApiControllerSwagger;
import com.example.memberservice.oauth.service.OAuthService;
import com.example.memberservice.oauth.service.dto.output.TokensOutput;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class OAuthApiController implements OAuthApiControllerSwagger {

    private final OAuthService oAuthService;

    private final JwtProperties jwtProperties;

    @PostMapping("/reissue")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ResponseDto<Null>> reissueAccessTokenByRefreshToken(
        HttpServletResponse httpServletResponse,
        @CookieValue(name = "refresh-token", required = false) String refreshToken) {

        if (refreshToken == null) {
            throw new BusinessException(ErrorCode.UNAUTHORIZATION);
        }

        TokensOutput output = oAuthService.reissueAccessTokenByRefreshToken(refreshToken);

        httpServletResponse.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + output.accessToken());
        httpServletResponse.addHeader(HttpHeaders.SET_COOKIE,
            CookieGenerator.createCookies("refresh-token", output.refreshToken(),
                jwtProperties.getRefreshTokenTtl()));

        return null;
    }


    @DeleteMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ResponseDto<Null>> logoutMemberByRefreshToken(
        @CookieValue("refresh-token") String refreshToken) {

        return null;
    }

}
