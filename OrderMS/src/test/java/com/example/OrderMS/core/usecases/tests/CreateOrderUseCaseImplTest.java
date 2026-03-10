package com.example.OrderMS.core.usecases.tests;


import com.example.OrderMS.core.entities.Order;
import com.example.OrderMS.core.gateways.OrderGateway;
import com.example.OrderMS.core.usecases.OrderInput;
import com.example.OrderMS.core.usecases.OrderOutput;
import com.example.OrderMS.core.usecases.create.CreateOrderUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateOrderUseCaseImplTest {

    @Mock
    private OrderGateway orderGateway;

    @InjectMocks
    private CreateOrderUseCaseImpl createOrderUseCase;

    @Test
    void shouldCreateOrderSuccessfully() {
        OrderInput input = new OrderInput(1L, 2L, 3, new BigDecimal("50.00"));

        Order savedOrder = new Order(1L, 2L, 3, new BigDecimal("50.00"));
        savedOrder.setId(10L); // simula o ID gerado pelo banco

        when(orderGateway.save(any(Order.class))).thenReturn(savedOrder);

        OrderOutput output = createOrderUseCase.execute(input);

        assertNotNull(output);
        assertEquals(10L, output.getOrderId());
        verify(orderGateway, times(1)).save(any(Order.class));
    }


    @Test
    void shouldThrowExceptionWhenQuantityIsZero() {
        assertThrows(RuntimeException.class, () ->
                new Order(1L, 2L, 0, new BigDecimal("50.00"))
        );
    }

    @Test
    void shouldThrowExceptionWhenPriceIsZero() {
        assertThrows(IllegalArgumentException.class, () ->
                new Order(1L, 2L, 3, BigDecimal.ZERO)
        );
    }
}