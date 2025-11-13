package com.example.profileservice.resume.api;


import com.example.profileservice.common.model.vo.ResponseDto;
import com.example.profileservice.experience.model.dto.request.ExperienceRequest;
import com.example.profileservice.experience.model.dto.response.ExperienceResponse;
import com.example.profileservice.resume.model.dto.request.ResumeCreateRequest;
import com.example.profileservice.resume.model.dto.request.ResumeUpdateRequest;
import com.example.profileservice.resume.model.dto.response.ResumeDetailResponse;
import com.example.profileservice.resume.model.dto.response.ResumeSimpleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Resume API", description = "프리랜서 이력서 및 경력 관리")
public interface ResumeApiController {

    String MEMBER_CODE_HEADER = "X-CODE";

    // 이력서 목록 조회
    @Operation(summary = "내 이력서 목록 조회", description = "로그인된 프리랜서가 작성한 모든 이력서 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "이력서 목록 조회 성공",
            content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    ResponseEntity<List<ResumeSimpleResponse>> getMyResumes(String memberCode);

    // 이력서 등록
    @Operation(summary = "이력서 등록", description = "새로운 이력서를 등록합니다. (최초 등록 시 Member 모듈에 'can_work' 상태 변경 이벤트 발생)")
    @ApiResponse(responseCode = "200", description = "이력서 등록 성공",
            content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    @ApiResponse(responseCode = "400", description = "입력 값 유효성 오류 (INVALID_INPUT_VALUE)",
            content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    ResponseEntity<ResumeDetailResponse> createResume(
            String memberCode,
            @Valid @RequestBody ResumeCreateRequest request);

    // 이력서 상세 조회
    @Operation(summary = "이력서 상세 조회", description = "특정 이력서의 상세 정보와 포함된 모든 경력/경험을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "이력서 상세 조회 성공",
            content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    @ApiResponse(responseCode = "404", description = "이력서를 찾을 수 없음 (RESUME_NOT_FOUND)",
            content = @Content(schema = @Schema(implementation = ResponseDto.class),
                    examples = @ExampleObject(value = "{\"code\": 3301, \"message\": \"요청하신 이력서를 찾을 수 없습니다.\"}")))
    @ApiResponse(responseCode = "403", description = "접근 권한 없음 (UNAUTHORIZED_RESUME_ACCESS)",
            content = @Content(schema = @Schema(implementation = ResponseDto.class),
                    examples = @ExampleObject(value = "{\"code\": 3302, \"message\": \"해당 이력서에 대한 접근 권한이 없습니다.\"}")))
    ResponseEntity<ResumeDetailResponse> getResumeDetail(
            String memberCode,
            @PathVariable String resumeCode);

    // 이력서 수정
    @Operation(summary = "이력서 수정", description = "특정 이력서의 제목, 내용, 링크 등을 수정합니다.")
    @ApiResponse(responseCode = "200", description = "이력서 수정 성공",
            content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    @ApiResponse(responseCode = "404", description = "이력서를 찾을 수 없음 (RESUME_NOT_FOUND)",
            content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    @ApiResponse(responseCode = "403", description = "접근 권한 없음 (UNAUTHORIZED_RESUME_ACCESS)",
            content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    @ApiResponse(responseCode = "400", description = "입력 값 유효성 오류 (INVALID_INPUT_VALUE)",
            content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    ResponseEntity<ResumeDetailResponse> updateResume(
            String memberCode,
            @PathVariable String resumeCode,
            @Valid @RequestBody ResumeUpdateRequest request);

    // 이력서 삭제
    @Operation(summary = "이력서 삭제", description = "특정 이력서를 완전히 삭제합니다.")
    @ApiResponse(responseCode = "204", description = "이력서 삭제 성공 (No Content)")
    @ApiResponse(responseCode = "404", description = "이력서를 찾을 수 없음 (RESUME_NOT_FOUND)",
            content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    @ApiResponse(responseCode = "403", description = "접근 권한 없음 (UNAUTHORIZED_RESUME_ACCESS)",
            content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    ResponseEntity<Void> deleteResume(
            String memberCode,
            @PathVariable String resumeCode);

    // 경력/경험 등록
    @Operation(summary = "경력/경험 등록", description = "특정 이력서에 새로운 경력/경험 항목을 등록합니다.")
    @ApiResponse(responseCode = "200", description = "경력/경험 등록 성공",
            content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    @ApiResponse(responseCode = "404", description = "이력서를 찾을 수 없음 (RESUME_NOT_FOUND)",
            content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    @ApiResponse(responseCode = "403", description = "이력서 접근 권한 없음 (UNAUTHORIZED_RESUME_ACCESS)",
            content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    @ApiResponse(responseCode = "400", description = "입력 값 유효성 오류 (INVALID_INPUT_VALUE)",
            content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    ResponseEntity<ExperienceResponse> createExperience(
            String memberCode,
            @PathVariable String resumeCode,
            @Valid @RequestBody ExperienceRequest request);

    // 경력/경험 수정
    @Operation(summary = "경력/경험 수정", description = "특정 경력/경험 항목을 수정합니다.")
    @ApiResponse(responseCode = "200", description = "경력/경험 수정 성공",
            content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    @ApiResponse(responseCode = "404", description = "이력서를 찾을 수 없음 (RESUME_NOT_FOUND) 또는 경력 항목을 찾을 수 없음 (EXPERIENCE_NOT_FOUND)",
            content = @Content(schema = @Schema(implementation = ResponseDto.class),
                    examples = @ExampleObject(name = "경력/경험 없음", value = "{\"code\": 3101, \"message\": \"요청하신 경력/경험 항목을 찾을 수 없습니다.\"}")))
    @ApiResponse(responseCode = "403", description = "이력서 접근 권한 없음 (UNAUTHORIZED_RESUME_ACCESS)",
            content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    @ApiResponse(responseCode = "400", description = "입력 값 유효성 오류 (INVALID_INPUT_VALUE)",
            content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    ResponseEntity<ExperienceResponse> updateExperience(
            String memberCode,
            @PathVariable String resumeCode,
            @PathVariable String experienceCode, @Valid @RequestBody ExperienceRequest request);

    // 경력/경험 삭제
    @Operation(summary = "경력/경험 삭제", description = "특정 경력/경험 항목을 삭제합니다.")
    @ApiResponse(responseCode = "204", description = "경력/경험 삭제 성공 (No Content)")
    @ApiResponse(responseCode = "404", description = "이력서를 찾을 수 없음 (RESUME_NOT_FOUND) 또는 경력 항목을 찾을 수 없음 (EXPERIENCE_NOT_FOUND)",
            content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    @ApiResponse(responseCode = "403", description = "이력서 접근 권한 없음 (UNAUTHORIZED_RESUME_ACCESS)",
            content = @Content(schema = @Schema(implementation = ResponseDto.class)))
    ResponseEntity<Void> deleteExperience(
            String memberCode,
            @PathVariable String resumeCode,
            @PathVariable String experienceCode);
}
