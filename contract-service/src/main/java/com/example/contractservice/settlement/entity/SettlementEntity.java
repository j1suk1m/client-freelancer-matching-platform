package com.example.contractservice.settlement.entity;

import com.example.contractservice.settlement.common.SettlementStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "settlements")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SettlementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false, columnDefinition = "CHAR(36)")
    private String code;

    @Column(name = "receiver_code", nullable = false, columnDefinition = "CHAR(36)")
    private String receiverCode;

    @Column(name = "contract_code", nullable = false, columnDefinition = "CHAR(36)")
    private String contractCode;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SettlementStatus status;

    @Column(name = "progressing_at", nullable = false)
    private Instant progressingAt;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "processed_at")
    private Instant processedAt;

}
