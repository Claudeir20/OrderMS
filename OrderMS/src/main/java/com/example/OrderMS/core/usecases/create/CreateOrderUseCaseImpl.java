package com.example.OrderMS.core.usecases.create;

import com.example.OrderMS.core.entities.Order;
import com.example.OrderMS.core.events.EventPublisher;
import com.example.OrderMS.core.gateways.OrderGateway;
import com.example.OrderMS.core.usecases.OrderInput;
import com.example.OrderMS.core.usecases.OrderOutput;

public class CreateOrderUseCaseImpl implements CreateOrderUseCase {

    private final OrderGateway orderGateway;
    private final EventPublisher eventPublisher;

    public CreateOrderUseCaseImpl(OrderGateway orderGateway, EventPublisher eventPublisher){
        this.orderGateway = orderGateway;
        this.eventPublisher = eventPublisher;
    }


    @Override
    public OrderOutput execute(OrderInput input){
        Order order = new Order(
                input.getUserId(),
                input.getProductId(),
                input.getQuantity(),
                input.getProductPrice()
        );

        Order saveOrder = orderGateway.save(order);
        order.assignId(saveOrder.getId());
        order.markAsCreated();
        eventPublisher.publish(order.pullEvents());

        return new OrderOutput(saveOrder.getId());

    }
}

