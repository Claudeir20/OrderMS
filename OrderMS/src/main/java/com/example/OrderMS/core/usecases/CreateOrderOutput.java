package com.example.OrderMS.core.usecases;

public class CreateOrderOutput {
    private final Long orderId;

    public CreateOrderOutput(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }
}