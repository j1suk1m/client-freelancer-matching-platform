package com.example.communicationservice.controller.dto.response;

import com.example.communicationservice.entity.ChatMessage;

import java.time.LocalDateTime;

public record ChatMessageSendResponse(
    String messageId,
    String roomId,
    String senderCode,
    String content,
    LocalDateTime sentAt
) {
    public static ChatMessageSendResponse from(ChatMessage message) {
        return new ChatMessageSendResponse(
            message.getId(),
            message.getRoomId(),
            message.getSenderCode(),
            message.getContent(),
            message.getSentAt()
        );
    }
}
