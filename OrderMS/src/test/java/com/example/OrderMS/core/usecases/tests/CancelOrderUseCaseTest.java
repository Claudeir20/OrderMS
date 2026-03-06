package com.example.OrderMS.core.usecases.tests;

import com.example.OrderMS.core.entities.Order;
import com.example.OrderMS.core.gateways.OrderGateway;
import com.example.OrderMS.core.usecases.cancel.CancelOrderUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CancelOrderUseCaseImplTest {

    @Mock
    private OrderGateway orderGateway;

    @InjectMocks
    private CancelOrderUseCaseImpl cancelOrderUseCase;

    @Test
    void shouldCancelOrderSuccessfully() {
        Order order = new Order(1L, 2L, 3, new BigDecimal("50.00"));

        when(orderGateway.findById(1L)).thenReturn(Optional.of(order));

        cancelOrderUseCase.execute(1L);

        verify(orderGateway, times(1)).save(order);
    }

    @Test
    void shouldThrowExceptionWhenOrderNotFound() {
        when(orderGateway.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () ->
                cancelOrderUseCase.execute(1L)
        );
    }

    @Test
    void shouldThrowExceptionWhenCancelConfirmedOrder() {
        Order order = new Order(1L, 2L, 3, new BigDecimal("50.00"));
        order.markAsConfirmed();

        when(orderGateway.findById(1L)).thenReturn(Optional.of(order));

        assertThrows(IllegalArgumentException.class, () ->
                cancelOrderUseCase.execute(1L)
        );
    }
}