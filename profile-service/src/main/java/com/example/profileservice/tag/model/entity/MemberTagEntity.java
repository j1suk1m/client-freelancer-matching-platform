package com.example.profileservice.tag.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "members_tags")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberTagEntity {

    // 연관된 멤버의 코드 FK
    @Column(name = "member_code", columnDefinition = "VARCHAR(36)", nullable = false)
    private String memberCode;

    // 연관된 태그의 코드 FK
    @Column(name = "tag_code", columnDefinition = "VARCHAR(36)", nullable = false)
    private String tagCode;

    @Builder
    public MemberTagEntity(String memberCode, String tagCode) {
        this.memberCode = memberCode;
        this.tagCode = tagCode;
    }
}
