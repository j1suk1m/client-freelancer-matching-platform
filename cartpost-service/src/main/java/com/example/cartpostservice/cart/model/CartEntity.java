package com.example.cartpostservice.cart.model;

import com.example.cartpostservice.common.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cart_itmes")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartEntity extends BaseEntity {

    @Column(name = "member_code", nullable = false, updatable = false)
    private String memberCode;

    @Column(name = "item_code", nullable = false, updatable = false)
    private String itemCode;

}
