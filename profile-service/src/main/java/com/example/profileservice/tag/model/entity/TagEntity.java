package com.example.profileservice.tag.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tags")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagEntity {

    // 기술명
    @Column(length = 100, nullable = false, unique = true)
    private String skill;

    @Builder
    public TagEntity(String skill) {
        this.skill = skill;
    }
}
