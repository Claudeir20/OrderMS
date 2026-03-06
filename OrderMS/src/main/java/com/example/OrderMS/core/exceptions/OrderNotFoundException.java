package com.example.OrderMS.core.exceptions;

public class OrderNotFoundException extends  DomainException{
    public OrderNotFoundException(Long id ) {
        super("Order not found with id: " + id);
    }
}
