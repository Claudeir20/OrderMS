package com.example.OrderMS.core.usecases.getorder;

import com.example.OrderMS.core.entities.Order;

public interface GetOrderByIdUseCase {
    Order execute(Long id);
}
