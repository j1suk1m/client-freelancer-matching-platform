package com.example.cartpostservice.cart.repository;

import com.example.cartpostservice.cart.model.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, String> {

}
