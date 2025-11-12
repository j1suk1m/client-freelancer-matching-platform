package com.example.profileservice.tag.api;

import com.example.profileservice.common.model.vo.BaseResponse;
import com.example.profileservice.tag.model.dto.request.TagRequest;
import com.example.profileservice.tag.model.dto.response.TagResponse;
import com.example.profileservice.tag.service.TagService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController implements TagApiController {

    // Gateway 환경이 구축되지 않았을 때를 위한 임시 기본값
    private static final String DEFAULT_MEMBER_CODE = "member-uuid-code-001";

    private final TagService tagService;

    // 전체 태그 목록 조회
    @Override
    public ResponseEntity<BaseResponse<List<TagResponse>>> getAllTags() {
        List<TagResponse> tags = tagService.getAllTags();

        return ResponseEntity.ok(BaseResponse.success(tags));
    }

    // 새 태그 등록
    @Override
    public ResponseEntity<BaseResponse<TagResponse>> createTag(@Valid @RequestBody TagRequest request) {
        TagResponse newTag = tagService.createTag(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse.success(newTag));
    }

    // 회원 태그 목록 조회
    @Override
    public ResponseEntity<BaseResponse<List<TagResponse>>> getMyTags(
            @RequestHeader(value = "X-User-Code", defaultValue = DEFAULT_MEMBER_CODE) String memberCode
    ) {
        List<TagResponse> myTags = tagService.getMyTags(memberCode);

        return ResponseEntity.ok(BaseResponse.success(myTags));
    }

    // 회원 태그 연결
    @Override
    public ResponseEntity<BaseResponse<Void>> linkMemberTag(@PathVariable String tagCode,
            @RequestHeader(value = "X-User-Code", defaultValue = DEFAULT_MEMBER_CODE) String memberCode) {
        tagService.linkMemberTag(memberCode, tagCode);

        return ResponseEntity.ok(BaseResponse.success());
    }

    // 회원 태그 연결 해제
    @Override
    public ResponseEntity<BaseResponse<Void>> unlinkMemberTag(@PathVariable String tagCode,
            @RequestHeader(value = "X-User-Code", defaultValue = DEFAULT_MEMBER_CODE) String memberCode) {
        tagService.unlinkMemberTag(memberCode, tagCode);

        return ResponseEntity.ok(BaseResponse.success());
    }
}
