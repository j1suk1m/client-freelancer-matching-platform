package com.example.communicationservice.controller.dto.response;

import com.example.communicationservice.entity.ChatRoom;

import java.time.LocalDateTime;

public record ChatRoomReadResponse(
    String id,
    String name,
    LocalDateTime updatedAt
) {
    public static ChatRoomReadResponse from(ChatRoom chatRoom) {
        return new ChatRoomReadResponse(
            chatRoom.getId(),
            chatRoom.getName(),
            chatRoom.getUpdatedAt()
        );
    }
}
