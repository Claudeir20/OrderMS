package com.example.OrderMS.core.usecases.creater;

import com.example.OrderMS.core.entities.Order;
import com.example.OrderMS.core.gateways.OrderGateway;

public class CreateOrderUseCaseImpl implements CreateOrderUseCase {

    private final OrderGateway orderGateway;

    public CreateOrderUseCaseImpl(OrderGateway orderGateway){
        this.orderGateway = orderGateway;
    }


    @Override
    public CreateOrderOutput execute(CreateOrderInput input){
        Order order = new Order(
                input.getUserId(),
                input.getProductId(),
                input.getQuantity(),
                input.getProductPrice()
        );

        Order saveOrder = orderGateway.save(order);

        return new CreateOrderOutput((saveOrder.getId()));

    }
}

