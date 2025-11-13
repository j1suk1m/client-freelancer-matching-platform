package com.example.cartpostservice.common.dto;

public final class EmptyDto {
    private static final EmptyDto INSTANCE = new EmptyDto();

    private EmptyDto() {}

    public static EmptyDto getInstance() {
        return INSTANCE;
    }
}