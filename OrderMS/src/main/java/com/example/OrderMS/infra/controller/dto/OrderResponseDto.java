package com.example.OrderMS.infra.controller.dto;

import com.example.OrderMS.core.entities.enums.StatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderResponseDto {
    private final Long id;
    private final Long userId;
    private final Long productId;
    private final int quantity;
    private final BigDecimal productPrice;
    private final StatusEnum status;
    private final LocalDateTime createdAt;

    public OrderResponseDto(Long id,
                            Long userId,
                            Long productId,
                            int quantity,
                            BigDecimal productPrice,
                            StatusEnum status,
                            LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.productPrice = productPrice;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

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

    public StatusEnum getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
