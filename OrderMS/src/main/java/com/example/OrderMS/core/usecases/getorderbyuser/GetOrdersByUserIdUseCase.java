package com.example.OrderMS.core.usecases.getorderbyuser;

import com.example.OrderMS.core.entities.Order;

import java.util.List;

public interface GetOrdersByUserIdUseCase {
   List<Order> execute(Long userId);
}
