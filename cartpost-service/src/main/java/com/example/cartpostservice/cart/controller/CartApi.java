package com.example.cartpostservice.cart.controller;

import com.example.cartpostservice.common.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@Tag(name = "Cart API", description = "장바구니 API 명세")
public interface CartApi {

    @Operation(summary = "장바구니 아이템 조회", description = "X-CODE 헤더를 기준으로 장바구니 목록을 조회합니다.")
    @Parameters(value = {
            @Parameter(
                    name = "X-CODE", // 실제 헤더의 이름
                    description = "클라이언트 식별을 위한 고유 코드",
                    required = true,
                    in = ParameterIn.HEADER, // 헤더라고 명시
                    example = "CLIENT-ABC-12345"
            )
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = ResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 (X-CODE 헤더 누락)",
                    content = @Content(schema = @Schema(implementation = ResponseDto.class))),
    })
    ResponseEntity<ResponseDto<?>> getCartItems(@RequestHeader(name = "X-CODE") String xCode);


    @Operation(summary = "장바구니 아이템 삭제", description = "X-CODE 헤더를 기준으로 경로 변수로 받은 아이템을 삭제합니다.")
    ResponseEntity<ResponseDto<?>> deleteCartItem(@RequestHeader(name = "X-CODE") String xCode,
            @PathVariable String itemCode);
}
