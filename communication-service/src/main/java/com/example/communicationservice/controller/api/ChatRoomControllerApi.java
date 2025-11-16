package com.example.communicationservice.controller.api;

import com.example.communicationservice.common.response.ResponseDto;
import com.example.communicationservice.controller.dto.request.ChatRoomCreateRequest;
import com.example.communicationservice.controller.dto.response.ChatRoomCreateResponse;
import com.example.communicationservice.controller.dto.response.ChatRoomListReadResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;

@Tag(name = "ChatRoom API", description = "채팅방 CRUD")
public interface ChatRoomControllerApi {

    @Operation(
        summary = "채팅방 생성",
        description = "채팅방 이름과 참여할 회원 코드 목록으로 새로운 채팅방을 생성합니다."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "채팅방 생성 성공"),
        @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    ResponseEntity<ResponseDto<ChatRoomCreateResponse>> createRoom(
        @RequestBody(
            description = "채팅방 생성 요청 DTO",
            required = true
        )
        ChatRoomCreateRequest request,

        @Parameter(
            name = "X-CODE",
            description = "현재 로그인한 회원 코드",
            required = true,
            in = ParameterIn.HEADER,
            example = "abc-12345-ABC"
        )
        String currentMemberCode
    );

    @Operation(
        summary = "채팅방 목록 조회",
        description = "현재 로그인한 회원이 참여하는 채팅방 목록을 최신순으로 조회합니다."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "채팅방 목록 조회 성공"),
        @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    ResponseEntity<ResponseDto<ChatRoomListReadResponse>> findRooms(
        @ParameterObject
        @PageableDefault(
            size = 10,
            sort = "updatedAt",
            direction = Sort.Direction.DESC
        )
        Pageable pageable,

        @Parameter(
            name = "X-CODE",
            description = "현재 로그인한 회원 코드",
            required = true,
            in = ParameterIn.HEADER,
            example = "abc-12345-ABC"
        )
        String currentMemberCode
    );

}
