package com.example.cartpostservice.commissions.service;

import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface CrudService<T, code> {

    public String create(T requestDto);

    public Optional<T> read(code code);

    public void update(T requestDto, code code);

    public void delete(code code);

    public boolean exist(code ownerCode, code targetCode);
}