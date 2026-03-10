package com.example.OrderMS.infra.controller.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class CreateOrderRequest {

    @NotNull @Positive
    private Long userId;

    @NotNull @Positive
    private Long productId;

    @Positive
    private int quantity;

    @NotNull @Positive
    private BigDecimal productPrice;


    public Long getUserId() {
        return userId;
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }
}
