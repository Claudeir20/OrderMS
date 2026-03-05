package com.example.OrderMS.core.usecases.tests;

import com.example.OrderMS.core.entities.Order;
import com.example.OrderMS.core.entities.enums.StatusEnum;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void shouldCreateOrderSuccessfully() {
        Order order = new Order(1L, 2L, 3, new BigDecimal("50.00"));
        assertNotNull(order);
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


    @Test
    void shouldCancelOrder() {
        Order order = new Order(1L, 2L, 3, new BigDecimal("50.00"));
        order.cancel();
        assertEquals(StatusEnum.CANCELLED, order.getStatus());
    }

    @Test
    void shouldThrowExceptionWhenCancelConfirmedOrder() {
        Order order = new Order(1L, 2L, 3, new BigDecimal("50.00"));
        order.markAsConfirmed();
        assertThrows(IllegalArgumentException.class, order::cancel);
    }

    @Test
    void shouldConfirmOrder() {
        Order order = new Order(1L, 2L, 3, new BigDecimal("50.00"));
        order.markAsConfirmed();
        assertEquals(StatusEnum.CONFIRMED, order.getStatus());
    }

    @Test
    void shouldChangeQuantity() {
        Order order = new Order(1L, 2L, 3, new BigDecimal("50.00"));
        order.changeQuantity(5);
        assertEquals(5, order.getQuantity());
    }

    @Test
    void shouldCalculateTotal() {
        Order order = new Order(1L, 2L, 3, new BigDecimal("50.00"));
        assertEquals(new BigDecimal("150.00"), order.calculateTotal());
    }
}