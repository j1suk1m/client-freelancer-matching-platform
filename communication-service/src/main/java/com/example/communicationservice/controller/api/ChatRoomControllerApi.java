package com.example.communicationservice.controller.api;

import com.example.communicationservice.common.response.ResponseDto;
import com.example.communicationservice.controller.dto.request.ChatRoomCreateRequest;
import com.example.communicationservice.controller.dto.response.ChatRoomCreateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Tag(name = "ChatRoom API", description = "채팅방 CRUD")
public interface ChatRoomControllerApi {

    @Operation(
        summary = "채팅방 생성",
        description = "새로운 채팅방을 생성합니다.",
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "채팅방 생성 성공",
                content = @Content(schema = @Schema(implementation = ChatRoomCreateResponse.class))
            ),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
        }
    )
    @PostMapping
    ResponseEntity<ResponseDto<ChatRoomCreateResponse>> createRoom(
        @Parameter(description = "채팅방 생성 요청 DTO", required = true)
        @Valid @RequestBody ChatRoomCreateRequest request,

        @Parameter(hidden = true)
        @RequestHeader(name = "X-CODE", required = false) String currentMemberCode
    );

}
