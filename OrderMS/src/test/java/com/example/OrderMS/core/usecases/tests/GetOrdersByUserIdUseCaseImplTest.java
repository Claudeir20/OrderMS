package com.example.OrderMS.core.usecases.tests;

import com.example.OrderMS.core.entities.Order;
import com.example.OrderMS.core.gateways.OrderGateway;
import com.example.OrderMS.core.usecases.getorderbyuser.GetOrdersByUserIdUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;

import java.math.BigDecimal;
import java.util.List;

import static jdk.internal.classfile.impl.verifier.VerifierImpl.verify;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetOrdersByUserIdUseCaseImplTest {

    @Mock
    private OrderGateway orderGateway;

    @InjectMocks
    private GetOrdersByUserIdUseCaseImpl getOrdersByUserIdUseCase;

    @Test
    void shouldGetOrdersByUserIdSuccessfully() {
        Order order1 = new Order(1L, 2L, 3, new BigDecimal("50.00"));
        Order order2 = new Order(1L, 3L, 1, new BigDecimal("100.00"));

        when(orderGateway.findByUserId(1L)).thenReturn(List.of(order1, order2));

        List<Order> result = getOrdersByUserIdUseCase.execute(1L);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(orderGateway, times(1)).findByUserId(1L);
    }

    private OrderGateway verify(OrderGateway orderGateway, VerificationMode times) {
        return orderGateway;
    }

    @Test
    void shouldReturnEmptyListWhenUserHasNoOrders() {
        when(orderGateway.findByUserId(1L)).thenReturn(List.of());

        List<Order> result = getOrdersByUserIdUseCase.execute(1L);

        assertNotNull(result);
        assertEquals(0, result.size());
        verify(orderGateway, times(1)).findByUserId(1L);
    }
}