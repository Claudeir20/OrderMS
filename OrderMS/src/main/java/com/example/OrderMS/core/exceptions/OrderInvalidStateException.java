package com.example.OrderMS.core.exceptions;

public class OrderInvalidStateException extends DomainException {
    public OrderInvalidStateException(String message) {
        super(message);
    }
}
