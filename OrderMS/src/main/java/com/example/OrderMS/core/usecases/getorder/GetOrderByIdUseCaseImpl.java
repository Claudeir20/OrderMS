package com.example.OrderMS.core.usecases.getorder;

import com.example.OrderMS.core.entities.Order;
import com.example.OrderMS.core.gateways.OrderGateway;

public class GetOrderByIdUseCaseImpl implements GetOrderByIdUseCase {

    private final OrderGateway orderGateway;

    public GetOrderByIdUseCaseImpl(OrderGateway orderGateway){
        this.orderGateway = orderGateway;
    }

    @Override
    public Order execute(Long id) {

         return  orderGateway.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found."));

    }
}
