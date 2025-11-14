package com.example.cartpostservice.cart.service;

import com.example.cartpostservice.cart.service.dto.request.CartItemDeleteCommand;
import com.example.cartpostservice.cart.service.dto.request.CartItemGetCommand;
import com.example.cartpostservice.cart.service.dto.response.CartItemGetResult;
import org.springframework.stereotype.Service;

@Service
public interface CartService {

    public CartItemGetResult getCartItems(CartItemGetCommand cartItemGetCommand);

    public CartItemDeleteCommand deleteCartItems(CartItemDeleteCommand cartItemDeleteCommand);
}
