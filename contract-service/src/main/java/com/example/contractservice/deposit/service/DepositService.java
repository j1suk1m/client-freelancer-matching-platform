package com.example.contractservice.deposit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepositService {
    private final DepositRepository depositRepository;

}
