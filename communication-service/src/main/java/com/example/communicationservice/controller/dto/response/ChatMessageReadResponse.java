package com.example.communicationservice.controller.dto.response;

import java.time.LocalDateTime;

public record ChatMessageReadResponse(
    String id,
    String senderCode,
    String content,
    LocalDateTime sentAt
) {
}
