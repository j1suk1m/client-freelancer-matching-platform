package com.example.contractservice.contract.domain.exception;

public enum ContractErrorCode {
    NO_FREELANCERS(4000, "프리랜서가 없어 계약을 생성할 수 없습니다."),
    INVALID_MEMBER(4001, "존재하지 않는 회원으로 계약을 생성할 수 없습니다."),
    DELETED_MEMBER(4002, "탈퇴한 회원은 계약을 생성할 수 없습니다."),

    NO_CONTRACT(4010, "해당 계약이 존재하지 않습니다."), // TODO: 계약 코드를 넣을 수 있도록 개선
    NOT_CONTRACTOR(4011, "현재 로그인한 회원이 계약 성립자가 아닙니다."),
    NOT_REQUESTED_STATUS(4012, "요청 상태인 계약만 성립할 수 있습니다."),

    INVALID_PAYMENT_MEMBER(4020, "현재 로그인한 회원만이 자신의 계약을 결제할 수 있으며 클라이언트여야 합니다."),
    NOT_CONFIRMED_STATUS(4021, "계약 성사 상태인 계약만 결제할 수 있습니다.");

    private final int statusCode;
    private final String message;

    ContractErrorCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}
