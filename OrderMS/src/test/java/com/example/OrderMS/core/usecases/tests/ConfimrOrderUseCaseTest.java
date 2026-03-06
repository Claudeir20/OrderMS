package com.example.OrderMS.core.usecases.tests;

import com.example.OrderMS.core.entities.Order;
import com.example.OrderMS.core.gateways.OrderGateway;
import com.example.OrderMS.core.usecases.confirm.ConfirmOrderUseCaseImpl;
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
class ConfirmOrderUseCaseImplTest {

    @Mock
    private OrderGateway orderGateway;

    @InjectMocks
    private ConfirmOrderUseCaseImpl confirmOrderUseCase;

    @Test
    void shouldConfirmOrderSuccessfully() {
        Order order = new Order(1L, 2L, 3, new BigDecimal("50.00"));

        when(orderGateway.findById(1L)).thenReturn(Optional.of(order));

        confirmOrderUseCase.execute(1L);

        verify(orderGateway, times(1)).save(order);
    }

    @Test
    void shouldThrowExceptionWhenOrderNotFound() {
        when(orderGateway.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () ->
                confirmOrderUseCase.execute(1L)
        );
    }

    @Test
    void shouldThrowExceptionWhenConfirmCancelledOrder() {
        Order order = new Order(1L, 2L, 3, new BigDecimal("50.00"));
        order.cancel();

        when(orderGateway.findById(1L)).thenReturn(Optional.of(order));

        assertThrows(IllegalArgumentException.class, () ->
                confirmOrderUseCase.execute(1L)
        );
    }
}