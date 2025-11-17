package com.example.cartpostservice.commissions.service.dto.request;

import com.example.cartpostservice.common.model.vo.PaymentType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CommissionsSaveCommand(
        String memberCode,

        String title,

        String content,

        PaymentType paymentType,

        String unitAmount,

        LocalDate startedAt,

        LocalDate endedAt,

        String writerName
) {
}
