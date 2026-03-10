package com.example.OrderMS.core.events;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderCreated implements DomainEvent {

    private final Long orderId;
    private final Long userId;
    private final Long productId;
    private final  BigDecimal totalValue;
    private final LocalDateTime occurredAt;


    public OrderCreated(Long orderId, Long userId, Long productId, BigDecimal totalValue, LocalDateTime occurredAt){
        this.orderId = orderId;
        this.userId = userId;
        this.productId = productId;
        this.totalValue = totalValue;
        this.occurredAt = occurredAt;

    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getProductId() {
        return productId;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    @Override
    public LocalDateTime occurredAt() {
        return occurredAt;
    }
}
