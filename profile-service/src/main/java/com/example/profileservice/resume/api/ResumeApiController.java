package com.example.profileservice.resume.api;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Resume API", description = "프리랜서 이력서 및 경력 관리")
@RequestMapping("/api/resumes")
public interface ResumeApiController {
}
