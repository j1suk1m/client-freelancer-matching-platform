package com.example.profileservice.resume.repository;

import com.example.profileservice.resume.model.entity.ResumeEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends JpaRepository<ResumeEntity, Long> {

    // memberCode로 모든 이력서 조회 (삭제되지 않은 것만, 최신순)
    List<ResumeEntity> findAllByMemberCodeAndIsDeletedFalseOrderByCreatedAtDesc(String memberCode);

    // resumeCode로 이력서 상세 조회 (삭제되지 않은 것만)
    Optional<ResumeEntity> findByCodeAndIsDeletedFalse(String code);
}
