package com.example.OrderMS.infra.config;

import com.example.OrderMS.core.gateways.OrderGateway;
import com.example.OrderMS.core.usecases.getorderbyuser.GetOrdersByUserIdUseCase;
import com.example.OrderMS.core.usecases.getorderbyuser.GetOrdersByUserIdUseCaseImpl;
import com.example.OrderMS.core.usecases.cancel.CancelOrderUseCase;
import com.example.OrderMS.core.usecases.cancel.CancelOrderUseCaseImpl;
import com.example.OrderMS.core.usecases.confirm.ConfirmOrderUseCase;
import com.example.OrderMS.core.usecases.confirm.ConfirmOrderUseCaseImpl;
import com.example.OrderMS.core.usecases.creater.CreateOrderUseCase;
import com.example.OrderMS.core.usecases.creater.CreateOrderUseCaseImpl;
import com.example.OrderMS.core.usecases.getorder.GetOrderByIdUseCase;
import com.example.OrderMS.core.usecases.getorder.GetOrderByIdUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateOrderUseCase createOrderUseCase(OrderGateway orderGateway){
        return new CreateOrderUseCaseImpl(orderGateway);
    }

    @Bean
    public CancelOrderUseCase cancelOrderUseCase(OrderGateway orderGateway){
        return new CancelOrderUseCaseImpl(orderGateway);
    }

    @Bean
    public ConfirmOrderUseCase confirmOrderUseCase(OrderGateway orderGateway){
        return new ConfirmOrderUseCaseImpl(orderGateway);
    }

    @Bean
    public GetOrderByIdUseCase getOrderByIdUseCase(OrderGateway orderGateway){
        return new GetOrderByIdUseCaseImpl(orderGateway);
    }

    @Bean
    public GetOrdersByUserIdUseCase getOrdersByUserIdUseCase(OrderGateway orderGateway){
        return new GetOrdersByUserIdUseCaseImpl(orderGateway);
    }
}
