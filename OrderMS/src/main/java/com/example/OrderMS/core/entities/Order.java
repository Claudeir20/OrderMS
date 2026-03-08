package com.example.OrderMS.core.entities;

import com.example.OrderMS.core.entities.enums.StatusEnum;
import jakarta.persistence.EnumType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;

public class Order {
    private Long id;
    private Long userId;
    private Long productId;
    private int quantity;
    private BigDecimal productPrice;
    private StatusEnum status;
    private LocalDateTime createdAt;


    public  Order( Long userId, Long productId, int quantity, BigDecimal productPrice){
        if(quantity <= 0){
            throw new RuntimeException("Quantity must be greater than 0 ");
        }
        if(userId == null ){
            throw new RuntimeException("User is required");
        }

        if(productId == null ){
            throw new RuntimeException("Product is required");
        }

        if(productPrice.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Price must be greater than 0");
        }

        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.productPrice = productPrice;
        this.status = StatusEnum.PENDING;
        this.createdAt = LocalDateTime.now();
    }

    private Order(){}

    public static Order reconstruct(Long id, Long userId, Long productId, int quantity, BigDecimal productPrice, StatusEnum status, LocalDateTime createdAt) {
        Order order = new Order();
        order.id = id;
        order.userId = userId;
        order.productId = productId;
        order.quantity = quantity;
        order.productPrice = productPrice;
        order.status = status;
        order.createdAt = createdAt;
        return order;
    }



    public BigDecimal calculateTotal() {

       return productPrice
               .multiply(BigDecimal.valueOf(quantity))
               .setScale(2, RoundingMode.HALF_UP);
    }

    public void cancel(){
        if (status == StatusEnum.CONFIRMED){
            throw new IllegalArgumentException("Cannot cancel a confirmed order");
        }
        this.status = StatusEnum.CANCELLED;
    }

    public void changeQuantity(int newQuantity){
        if (newQuantity <= 0){
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        if (status == StatusEnum.CANCELLED){
            throw new IllegalArgumentException("This order has already been canceled");
        }

        if (status == StatusEnum.CONFIRMED){
            throw new IllegalArgumentException("This order is confirmed");
        }

        quantity = newQuantity;
    }

    public void markAsConfirmed(){
        if (status == StatusEnum.CANCELLED){
            throw new IllegalArgumentException("This order has already been canceled");
        }
        this.status = StatusEnum.CONFIRMED;

    }


    public Long getId() {
        return id;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public int getQuantity() {
        return quantity;
    }

    public  Long getUserId(){
        return userId;
    }

    public Long getProductId(){
        return productId;
    }

    public BigDecimal getProductPrice(){
        return productPrice;
    }
    public  LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStatus(StatusEnum status){
        this.status = status;
    }

    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }
}