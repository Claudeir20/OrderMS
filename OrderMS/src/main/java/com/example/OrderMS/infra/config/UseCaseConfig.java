package com.example.OrderMS.infra.config;

import com.example.OrderMS.core.events.EventPublisher;
import com.example.OrderMS.core.gateways.OrderGateway;
import com.example.OrderMS.core.usecases.getorderbyuser.GetOrdersByUserIdUseCase;
import com.example.OrderMS.core.usecases.getorderbyuser.GetOrdersByUserIdUseCaseImpl;
import com.example.OrderMS.core.usecases.cancel.CancelOrderUseCase;
import com.example.OrderMS.core.usecases.cancel.CancelOrderUseCaseImpl;
import com.example.OrderMS.core.usecases.confirm.ConfirmOrderUseCase;
import com.example.OrderMS.core.usecases.confirm.ConfirmOrderUseCaseImpl;
import com.example.OrderMS.core.usecases.create.CreateOrderUseCase;
import com.example.OrderMS.core.usecases.create.CreateOrderUseCaseImpl;
import com.example.OrderMS.core.usecases.getorder.GetOrderByIdUseCase;
import com.example.OrderMS.core.usecases.getorder.GetOrderByIdUseCaseImpl;
import com.example.OrderMS.infra.controller.mapper.OrderResponseMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateOrderUseCase createOrderUseCase(OrderGateway orderGateway, EventPublisher eventPublisher){
        return new CreateOrderUseCaseImpl(orderGateway, eventPublisher);
    }

    @Bean
    public CancelOrderUseCase cancelOrderUseCase(OrderGateway orderGateway, EventPublisher eventPublisher){
        return new CancelOrderUseCaseImpl(orderGateway, eventPublisher);
    }

    @Bean
    public ConfirmOrderUseCase confirmOrderUseCase(OrderGateway orderGateway, EventPublisher eventPublisher){
        return new ConfirmOrderUseCaseImpl(orderGateway, eventPublisher);
    }

    @Bean
    public GetOrderByIdUseCase getOrderByIdUseCase(OrderGateway orderGateway){
        return new GetOrderByIdUseCaseImpl(orderGateway);
    }

    @Bean
    public GetOrdersByUserIdUseCase getOrdersByUserIdUseCase(OrderGateway orderGateway){
        return new GetOrdersByUserIdUseCaseImpl(orderGateway);
    }

    @Bean
    public OrderResponseMapper orderResponseMapper(){
        return new OrderResponseMapper();
    }
}
