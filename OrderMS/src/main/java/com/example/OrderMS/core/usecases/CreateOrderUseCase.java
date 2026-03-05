package com.example.OrderMS.core.usecases;

import com.example.OrderMS.core.entities.Order;

public interface CreateOrderUseCase {
     CreateOrderOutput execute(CreateOrderInput input);
}
