package com.example.OrderMS.infra.controller.mapper;

import com.example.OrderMS.core.entities.Order;
import com.example.OrderMS.infra.controller.dto.OrderResponseDto;

public class OrderResponseMapper {

    public OrderResponseDto toResponse(Order order) {
        return new OrderResponseDto(
                order.getId(),
                order.getUserId(),
                order.getProductId(),
                order.getQuantity(),
                order.getProductPrice(),
                order.getStatus(),
                order.getCreatedAt()
        );
    }

}
