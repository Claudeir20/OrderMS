package com.example.OrderMS.core.events;

import java.time.LocalDateTime;


public class OrderCancelled implements  DomainEvent{

    private final Long orderId;
    private final Long userId;
    private final LocalDateTime occurredAt;

    public OrderCancelled(Long orderId, Long userId, LocalDateTime occurredAt){
        this.orderId = orderId;
        this.userId = userId;
        this.occurredAt = occurredAt;
    }


    public Long getOrderId() {
        return orderId;
    }

    public Long getUserId() {
        return userId;
    }



    @Override
    public LocalDateTime occurredAt() {
        return occurredAt;
    }
}
