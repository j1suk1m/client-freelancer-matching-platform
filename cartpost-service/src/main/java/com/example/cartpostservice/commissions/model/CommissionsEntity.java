package com.example.cartpostservice.commissions.model;

import com.example.cartpostservice.common.model.vo.PaymentType;
import com.example.cartpostservice.common.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Table(name = "commissions")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommissionsEntity extends BaseEntity {

    @Column(nullable = false)
    private String memberCode;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private PaymentType paymentType;

    @Column(nullable = false)
    private String unitAmount;

    @Column(nullable = false)
    private LocalDate startedAt;

    @Column(nullable = false)
    private LocalDate endedAt;

    @Column(nullable = false)
    private boolean isOpen = true;

    @Column(nullable = false)
    private String writerName;

    @Builder
    public CommissionsEntity(String memberCode, String title, String content, PaymentType paymentType, String unitAmount, LocalDate startedAt, LocalDate endedAt, String writerName){
        this.memberCode = memberCode;
        this.title = title;
        this.content = content;
        this.paymentType = paymentType;
        this.unitAmount = unitAmount;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.writerName = writerName;
    }
}
