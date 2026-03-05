package com.example.OrderMS.core.usecases;

import java.math.BigDecimal;

public class CreateOrderInput {
    private Long userId;
    private Long productId;
    private int quantity;
    private BigDecimal productPrice;

    public CreateOrderInput(Long userId, Long productId, int quantity, BigDecimal productPrice){

        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.productPrice = productPrice;
    }

    public int getQuantity(){
        return quantity;
    }

    public Long getUserId(){
        return userId;
    }


    public Long getProductId(){
        return productId;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }


}

