package com.example.cartpostservice.cart.repository;

import com.example.cartpostservice.cart.model.CartItemsEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItemsEntity, String> {

    List<CartItemsEntity> findByCartCode(String cartCode);
}
