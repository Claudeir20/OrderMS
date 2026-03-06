package com.example.OrderMS.infra.mapper;


import com.example.OrderMS.core.entities.Order;
import com.example.OrderMS.core.gateways.OrderGateway;
import com.example.OrderMS.infra.model.OrderEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public  OrderEntity toEntity(Order order){
        OrderEntity entity = new OrderEntity();
        entity.setId(order.getId());
        entity.setUserId(order.getUserId());
        entity.setProductId(order.getProductId());
        entity.setQuantity(order.getQuantity());
        entity.setProductPrice(order.getProductPrice());
        entity.setStatus(order.getStatus());
        entity.setCreatedAt(order.getCreatedAt());
        return entity;
    }

    public Order toDomain(OrderEntity entity) {
        return Order.reconstruct(
                entity.getId(),
                entity.getUserId(),
                entity.getProductId(),
                entity.getQuantity(),
                entity.getProductPrice(),
                entity.getStatus(),
                entity.getCreatedAt()
        );
    }
}
