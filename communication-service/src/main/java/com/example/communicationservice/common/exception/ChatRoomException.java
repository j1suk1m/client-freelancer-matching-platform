package com.example.communicationservice.common.exception;

import com.example.communicationservice.common.status.ResponseDtoStatus;
import lombok.Getter;

@Getter
public class ChatRoomException extends RuntimeException {

    private final ResponseDtoStatus status;

    public ChatRoomException(ResponseDtoStatus status) {
        super(status.getMessage());
        this.status = status;
    }

}
