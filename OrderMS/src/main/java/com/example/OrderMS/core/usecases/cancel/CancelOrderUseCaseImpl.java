package com.example.OrderMS.core.usecases.cancel;

import com.example.OrderMS.core.entities.Order;
import com.example.OrderMS.core.gateways.OrderGateway;

public class CancelOrderUseCaseImpl implements CancelOrderUseCase{

    private final OrderGateway orderGateway;

    public  CancelOrderUseCaseImpl(OrderGateway orderGateway){
        this.orderGateway = orderGateway;
    }


    @Override
    public void execute(Long id) {

        Order order = orderGateway.findById(id)
                .orElseThrow( () -> new RuntimeException("Order not found"));

        order.cancel();
        orderGateway.save(order);
    }
}
