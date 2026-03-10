package com.example.OrderMS.core.exceptions;

public class OrderValidationException extends DomainException {
    public OrderValidationException(String message) {
        super(message);
    }
}
