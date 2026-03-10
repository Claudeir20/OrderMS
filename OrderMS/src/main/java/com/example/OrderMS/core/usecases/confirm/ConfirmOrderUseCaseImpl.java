package com.example.OrderMS.core.usecases.confirm;

import com.example.OrderMS.core.entities.Order;
import com.example.OrderMS.core.events.EventPublisher;
import com.example.OrderMS.core.exceptions.OrderNotFoundException;
import com.example.OrderMS.core.gateways.OrderGateway;

public class ConfirmOrderUseCaseImpl implements ConfirmOrderUseCase{

    private final OrderGateway orderGateway;
    private final EventPublisher eventPublisher;

    public ConfirmOrderUseCaseImpl(OrderGateway orderGateway, EventPublisher eventPublisher){
        this.orderGateway = orderGateway;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void execute(Long id) {

        Order order = orderGateway.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        order.markAsConfirmed();
        orderGateway.save(order);
        eventPublisher.publish(order.pullEvents());

    }
}
