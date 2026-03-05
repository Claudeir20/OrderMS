package com.example.OrderMS.core.gateways;

import com.example.OrderMS.core.entities.Order;

import java.util.List;
import java.util.Optional;

public interface OrderGateway {
    Order save(Order order);
    Optional<Order>findById(Long id);
    List<Order> findByUserId(Long id);

}
