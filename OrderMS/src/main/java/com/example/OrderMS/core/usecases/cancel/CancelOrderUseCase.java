package com.example.OrderMS.core.usecases.cancel;

import com.example.OrderMS.core.entities.Order;

public interface CancelOrderUseCase {
    void execute(Long id);
}
