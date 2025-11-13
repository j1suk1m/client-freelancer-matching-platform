package com.example.profileservice.resume.api;

import com.example.profileservice.experience.model.dto.request.ExperienceRequest;
import com.example.profileservice.experience.model.dto.response.ExperienceResponse;
import com.example.profileservice.resume.model.dto.request.ResumeCreateRequest;
import com.example.profileservice.resume.model.dto.request.ResumeUpdateRequest;
import com.example.profileservice.resume.model.dto.response.ResumeDetailResponse;
import com.example.profileservice.resume.model.dto.response.ResumeSimpleResponse;
import com.example.profileservice.resume.service.ResumeService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resumes")
@RequiredArgsConstructor
public class ResumeController implements ResumeApiController {

    private final ResumeService resumeService;
    private static final String DEFAULT_MEMBER_CODE = "member-uuid-code-001";

    @Override
    @GetMapping("/me")
    public ResponseEntity<List<ResumeSimpleResponse>> getMyResumes(
            @RequestHeader(value = MEMBER_CODE_HEADER, defaultValue = DEFAULT_MEMBER_CODE) String memberCode
    ) {
        List<ResumeSimpleResponse> resumes = resumeService.getMyResumes(memberCode);

        return ResponseEntity.ok(resumes);
    }

    @Override
    @PostMapping
    public ResponseEntity<ResumeDetailResponse> createResume(
            @RequestHeader(value = MEMBER_CODE_HEADER, defaultValue = DEFAULT_MEMBER_CODE) String memberCode,
            @Valid @RequestBody ResumeCreateRequest request) {
        ResumeDetailResponse response = resumeService.createResume(memberCode, request);

        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("/{resumeCode}")
    public ResponseEntity<ResumeDetailResponse> getResumeDetail(
            @RequestHeader(value = MEMBER_CODE_HEADER, defaultValue = DEFAULT_MEMBER_CODE) String memberCode,
            @PathVariable String resumeCode) {
        ResumeDetailResponse response = resumeService.getResumeDetail(memberCode, resumeCode);

        return ResponseEntity.ok(response);
    }

    @Override
    @PatchMapping("/{resumeCode}")
    public ResponseEntity<ResumeDetailResponse> updateResume(
            @RequestHeader(value = MEMBER_CODE_HEADER, defaultValue = DEFAULT_MEMBER_CODE) String memberCode,
            @PathVariable String resumeCode,
            @Valid @RequestBody ResumeUpdateRequest request) {
        ResumeDetailResponse response = resumeService.updateResume(memberCode, resumeCode, request);

        return ResponseEntity.ok(response);
    }

    @Override
    @DeleteMapping("/{resumeCode}")
    public ResponseEntity<Void> deleteResume(
            @RequestHeader(value = MEMBER_CODE_HEADER, defaultValue = DEFAULT_MEMBER_CODE) String memberCode,
            @PathVariable String resumeCode
    ) {
        resumeService.deleteResume(memberCode, resumeCode);

        return ResponseEntity.noContent().build();
    }

    @Override
    @PostMapping("/{resumeCode}/experiences")
    public ResponseEntity<ExperienceResponse> createExperience(
            @RequestHeader(value = MEMBER_CODE_HEADER, defaultValue = DEFAULT_MEMBER_CODE) String memberCode,
            @PathVariable String resumeCode,
            @Valid @RequestBody ExperienceRequest request
    ) {
        ExperienceResponse response = resumeService.createExperience(memberCode, resumeCode, request);

        return ResponseEntity.ok(response);
    }

    @Override
    @PatchMapping("/{resumeCode}/experiences/{experienceCode}")
    public ResponseEntity<ExperienceResponse> updateExperience(
            @RequestHeader(value = MEMBER_CODE_HEADER, defaultValue = DEFAULT_MEMBER_CODE) String memberCode,
            @PathVariable String resumeCode,
            @PathVariable String experienceCode,
            @Valid @RequestBody ExperienceRequest request
    ) {
        ExperienceResponse response = resumeService.updateExperience(memberCode, resumeCode, experienceCode, request);

        return ResponseEntity.ok(response);
    }

    @Override
    @DeleteMapping("/{resumeCode}/experiences/{experienceCode}")
    public ResponseEntity<Void> deleteExperience(
            @RequestHeader(value = MEMBER_CODE_HEADER, defaultValue = DEFAULT_MEMBER_CODE) String memberCode,
            @PathVariable String resumeCode,
            @PathVariable String experienceCode
    ) {
        resumeService.deleteExperience(memberCode, resumeCode, experienceCode);

        return ResponseEntity.noContent().build();
    }
}
