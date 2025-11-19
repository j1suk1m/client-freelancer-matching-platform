package com.example.cartpostservice.kafka;

import com.example.cartpostservice.cart.controller.dto.response.ContractBriefWithNicknameResponse;
import com.example.cartpostservice.cart.controller.internal.ContractClient;
import com.example.cartpostservice.cart.model.CartItemsEntity;
import com.example.cartpostservice.cart.model.CartsEntity;
import com.example.cartpostservice.cart.model.vo.ContractStatus;
import com.example.cartpostservice.cart.repository.CartItemsRepository;
import com.example.cartpostservice.cart.repository.CartsRepository;
import com.example.cartpostservice.cart.service.CartService;
import com.example.cartpostservice.commissions.service.CommissionsService;
import com.example.cartpostservice.common.dto.ResponseDto;
import com.example.cartpostservice.common.exception.BusinessException;
import com.example.cartpostservice.common.exception.CustomStatusCode;
import com.example.cartpostservice.common.model.vo.PaymentType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hexagon.core.events.contract.ContractEvent;
import org.hexagon.core.events.member.MemberCreatedEvent;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Component
@RequiredArgsConstructor
// 클래스 레벨 리스너: 여러 토픽을 구독합니다.
@KafkaListener(
        topics = {
                "${member.topic.name}",   // Member 서버 이벤트 토픽
                "${contract.topic.name}"  // Contract 서버 이벤트 토픽
        },
        groupId = "${spring.kafka.consumer.group-id}" // 컨슈머 그룹 ID
)
public class CartPostKafka {

    private final CartsRepository cartsRepository;
    private final CartItemsRepository cartItemsRepository;
    private final ContractClient contractClient;

    /**
     * 1. 장바구니 생성 (K)
     * Member 서버에서 "사용자 생성" 메세지가 오면 실행
     * Payload: MemberCreatedEvent
     */
    @KafkaHandler
    @Transactional
    public void handleEvent(@Payload MemberCreatedEvent event) {
        CartsEntity cart = CartsEntity.builder()
                .memberCode(event.memberCode())
                .build();

        if(!cartsRepository.existsByMemberCode(event.memberCode())){
            cartsRepository.save(cart);
        }
    }

    /**
     * 2. 장바구니 아이템 추가 (K)
     * Contract 서버에서 "계약 CONFIRMED" 메세지가 오면 실행
     * Payload: ContractConfirmedEvent
     */
    @KafkaHandler
    @Transactional
    public void handleEvent(@Payload ContractEvent event) {
        CartsEntity cart = cartsRepository.findByMemberCode(event.memberCode())
                .orElseThrow(() -> new BusinessException(CustomStatusCode.NOT_FOUND_MEMBER));

        List<String> contractCode = List.of(event.contractCode());
        ResponseDto<List<ContractBriefWithNicknameResponse>> response = contractClient.getBriefInfo(contractCode);


        if(event.status().equals("CONFIRMED")){
            if(!cartItemsRepository.existsByContractCode(event.contractCode())){

                List<ContractBriefWithNicknameResponse> contractList = response.data();

                if (contractList == null || contractList.isEmpty()) {
                    throw new BusinessException(CustomStatusCode.NOT_FOUND_ITEM);
                }

                // 4. 리스트 중에서 현재 이벤트의 contractCode와 일치하는 정보 찾기
                ContractBriefWithNicknameResponse contractInfo = contractList.stream()
                        .filter(info -> info.code().equals(event.contractCode()))
                        .findFirst()
                        .orElseThrow(() -> new BusinessException(CustomStatusCode.NOT_FOUND_ITEM));

                CartItemsEntity item = CartItemsEntity.builder()
                        .contractCode(event.contractCode())
                        .cartCode(cart.getCode())
                        .status(ContractStatus.valueOf(event.status()))
                        .startedAt(contractInfo.startedAt())
                        .endedAt(contractInfo.endedAt())
                        .paymentType(PaymentType.valueOf(contractInfo.paymentType()))
                        .amount(String.valueOf(contractInfo.unitAmount()))
                        .build();

                cartItemsRepository.save(item);
            }
        }

    }

}
