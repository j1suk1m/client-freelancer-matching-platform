package com.example.profileservice.tag.service;

import com.example.profileservice.common.model.vo.ErrorCode;
import com.example.profileservice.common.model.vo.exception.CustomException;
import com.example.profileservice.tag.model.dto.request.TagRequest;
import com.example.profileservice.tag.model.dto.response.TagResponse;
import com.example.profileservice.tag.model.entity.MemberTagEntity;
import com.example.profileservice.tag.model.entity.TagEntity;
import com.example.profileservice.tag.repository.MemberTagRepository;
import com.example.profileservice.tag.repository.TagRepository;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TagService {

    private final TagRepository tagRepository;
    private final MemberTagRepository memberTagRepository;

    private TagResponse toResponse(TagEntity entity) {
        return new TagResponse(entity.getCode(), entity.getSkill());
    }

    // 전체 태그 목록 조회
    public List<TagResponse> getAllTags() {
        return tagRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // 새로운 기술 태그를 등록
    @Transactional
    public TagResponse createTag(TagRequest request) {
        // 1. 중복 등록 방지
        if (tagRepository.existsBySkillIgnoreCase(request.skill())) {
            throw new CustomException(ErrorCode.TAG_ALREADY_EXISTS);
        }

        // 2. TagEntity 생성 및 저장
        TagEntity newTag = TagEntity.builder()
                .skill(request.skill())
                .build();

        TagEntity savedTag = tagRepository.save(newTag);

        return toResponse(savedTag);
    }

    // 특정 회원이 등록한 태그 목록을 조회
    public List<TagResponse> getMyTags(String memberCode) {
        // 1. 회원의 모든 MemberTagEntity 조회
        List<MemberTagEntity> memberTags = memberTagRepository.findAllByMemberCode(memberCode);

        if (memberTags.isEmpty()) {
            return Collections.emptyList();
        }

        // 2. 연결된 TagEntity의 code를 추출
        List<String> tagCodes = memberTags.stream()
                .map(MemberTagEntity::getTagCode)
                .collect(Collectors.toList());

        // 3. 추출된 코드를 사용하여 TagEntity 목록을 한 번에 조회
        List<TagEntity> tags = tagRepository.findAllByCodeIn(tagCodes);

        return tags.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // 마이페이지에 특정 태그를 연결
    @Transactional
    public void linkMemberTag(String memberCode, String tagCode) {
        // 1. 태그 존재 여부 확인
        TagEntity tag = tagRepository.findByCode(tagCode)
                .orElseThrow(() -> new CustomException(ErrorCode.TAG_NOT_FOUND));

        // 2. 중복 연결 방지
        if (memberTagRepository.existsByMemberCodeAndTagCode(memberCode, tagCode)) {
            throw new CustomException(ErrorCode.MEMBER_TAG_ALREADY_CONNECTED,
                    "해당 회원은 이미 태그 [" + tag.getSkill() + "]를 연결했습니다.");
        }

        // 3. MemberTagEntity 생성 및 저장
        MemberTagEntity memberTag = MemberTagEntity.builder()
                .memberCode(memberCode)
                .tagCode(tagCode)
                .build();

        memberTagRepository.save(memberTag);
    }

    // 마이페이지에서 특정 태그 연결을 해제
    @Transactional
    public void unlinkMemberTag(String memberCode, String tagCode) {
        // 1. 연결된 MemberTagEntity 조회
        MemberTagEntity memberTag = memberTagRepository.findByMemberCodeAndTagCode(memberCode, tagCode)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_TAG_NOT_FOUND));

        // 2. 삭제
        memberTagRepository.delete(memberTag);
    }
}
