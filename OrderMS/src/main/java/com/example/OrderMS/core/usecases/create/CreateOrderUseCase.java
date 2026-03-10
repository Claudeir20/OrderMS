package com.example.OrderMS.core.usecases.create;

import com.example.OrderMS.core.usecases.OrderInput;
import com.example.OrderMS.core.usecases.OrderOutput;

public interface CreateOrderUseCase {
     OrderOutput execute(OrderInput input);
}
