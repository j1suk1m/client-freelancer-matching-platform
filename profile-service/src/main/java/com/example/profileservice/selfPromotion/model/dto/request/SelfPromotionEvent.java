package com.example.profileservice.selfPromotion.model.dto.request;

import com.example.profileservice.common.model.vo.OperationType;
import com.example.profileservice.selfPromotion.model.dto.response.SelfPromotionResponse;
import java.time.Instant;

public record SelfPromotionEvent(
        OperationType operationType,
        Instant createdAt,
        SelfPromotionResponse data
) {
    public static SelfPromotionEvent create(SelfPromotionResponse data) {
        return new SelfPromotionEvent(OperationType.CREATE, Instant.now(), data);
    }
    public static SelfPromotionEvent update(SelfPromotionResponse data) {
        return new SelfPromotionEvent(OperationType.UPDATE, Instant.now(), data);
    }
    // 삭제 시에는 코드만 보내는 것이 효율적일 수 있으나, 여기서는 일관성을 위해 전체 DTO를 사용
    public static SelfPromotionEvent delete(SelfPromotionResponse data) {
        return new SelfPromotionEvent(OperationType.DELETE, Instant.now(), data);
    }
}
