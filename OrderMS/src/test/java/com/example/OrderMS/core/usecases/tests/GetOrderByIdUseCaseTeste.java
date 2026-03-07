package com.example.OrderMS.core.usecases.tests;


import com.example.OrderMS.core.entities.Order;
import com.example.OrderMS.core.exceptions.OrderNotFoundException;
import com.example.OrderMS.core.gateways.OrderGateway;
import com.example.OrderMS.core.usecases.getorder.GetOrderByIdUseCase;
import com.example.OrderMS.core.usecases.getorder.GetOrderByIdUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.verification.VerificationMode;

import java.math.BigDecimal;
import java.util.Optional;

import static jdk.internal.classfile.impl.verifier.VerifierImpl.verify;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetOrderByIdUseCaseImplTest {

    @Mock
    private OrderGateway orderGateway;

    @InjectMocks
    private GetOrderByIdUseCaseImpl getOrderByIdUseCase;

    @Test
    void shouldGetOrderByIdSuccessfully() {
        Order order = new Order(1L, 2L, 3, new BigDecimal("50.00"));
        order.setId(1L);

        when(orderGateway.findById(1L)).thenReturn(Optional.of(order));

        Order result = getOrderByIdUseCase.execute(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(orderGateway, times(1)).findById(1L);
    }

    private OrderGateway verify(OrderGateway orderGateway, VerificationMode times) {
        return orderGateway;
    }

    @Test
    void shouldThrowExceptionWhenOrderNotFound() {
        when(orderGateway.findById(1L)).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () ->
                getOrderByIdUseCase.execute(1L)
        );
    }
}