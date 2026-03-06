package com.example.OrderMS.core.usecases.getorderbyuser;

import com.example.OrderMS.core.entities.Order;
import com.example.OrderMS.core.gateways.OrderGateway;

import java.util.List;

public class GetOrdersByUserIdUseCaseImpl implements GetOrdersByUserIdUseCase {


    private final OrderGateway orderGateway;

    public GetOrdersByUserIdUseCaseImpl(OrderGateway orderGateway){
        this.orderGateway = orderGateway;
    }

    @Override
    public List<Order> execute(Long userId) {
        return orderGateway.findByUserId(userId);


    }
}
