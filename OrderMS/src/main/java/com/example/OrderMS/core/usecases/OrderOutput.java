package com.example.OrderMS.core.usecases;

public class OrderOutput {
    private final Long orderId;

    public OrderOutput(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }
}