package com.example.cartpostservice.commissions.service;

import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface CrudService<Req, Res, code> {

    public String create(Req requestDto);

    public Optional<Res> read(code code);

    public void update(Req requestDto, code code);

    public void delete(code code);

    public boolean exist(code ownerCode, code targetCode);
}