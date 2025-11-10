package com.example.profileservice.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        // JWT 인증 설정을 위한 SecurityScheme 이름
        String jwtScheme = "jwtAuth";

        // API 기본 정보 설정
        Info info = new Info()
                .title("Profile Service API (프리랜서 프로필 모듈)")
                .description("프리랜서의 이력서, 경력/경험, 프로모션, 기술 태그, 평가 정보를 관리하는 마이크로 서비스입니다.");

        // JWT SecurityScheme 설정
        // Header를 통해 'Authorization: Bearer <token>' 형태로 JWT를 받도록 설정합니다.
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .name(jwtScheme)
                .in(SecurityScheme.In.HEADER)
                .description("JWT 토큰을 입력해주세요. (예: Bearer {token})");

        // SecurityRequirement 설정 (기본적으로 모든 API에 JWT 인증 요구)
        // 만약 인증이 필요 없는 API가 있다면, 해당 API 메서드에
        // @SecurityRequirement(name = "BearerAuth", scopes = {}) 등을 사용해 오버라이딩
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtScheme);

        // OpenAPI 객체 구성
        return new OpenAPI()
                .info(info)
                // Components에 Security Scheme 등록
                .components(new Components().addSecuritySchemes(jwtScheme, securityScheme))
                // 모든 API에 Security Requirement 적용 (전역 설정)
                .addSecurityItem(securityRequirement);
    }
}
