package com.example.OrderMS.core.usecases.confirm;

import com.example.OrderMS.core.entities.Order;
import com.example.OrderMS.core.gateways.OrderGateway;

public class ConfirmOrderUseCaseImpl implements ConfirmOrderUseCase{

    private final OrderGateway orderGateway;

    public ConfirmOrderUseCaseImpl(OrderGateway orderGateway){
        this.orderGateway = orderGateway;
    }

    @Override
    public void execute(Long id) {

        Order order = orderGateway.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.markAsConfirmed();
        orderGateway.save(order);

    }
}
