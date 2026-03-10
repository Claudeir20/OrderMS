package com.example.OrderMS.core.entities;

import com.example.OrderMS.core.entities.enums.StatusEnum;
import com.example.OrderMS.core.events.DomainEvent;
import com.example.OrderMS.core.events.OrderCancelled;
import com.example.OrderMS.core.events.OrderConfirmed;
import com.example.OrderMS.core.events.OrderCreated;
import com.example.OrderMS.core.exceptions.OrderInvalidStateException;
import com.example.OrderMS.core.exceptions.OrderValidationException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Order {
    private Long id;
    private Long userId;
    private Long productId;
    private int quantity;
    private BigDecimal productPrice;
    private StatusEnum status;
    private LocalDateTime createdAt;


    // Regra: pedido nasce valido e em status PENDING
    public Order(Long userId, Long productId, int quantity, BigDecimal productPrice) {
        // Regra: quantidade deve ser maior que zero
        if (quantity <= 0) {
            throw new OrderValidationException("Quantity must be greater than 0");
        }
        // Regra: usuario e obrigatorio
        if (userId == null) {
            throw new OrderValidationException("User is required");
        }
        // Regra: produto e obrigatorio
        if (productId == null) {
            throw new OrderValidationException("Product is required");
        }
        // Regra: preco deve ser maior que zero
        if (productPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new OrderValidationException("Price must be greater than 0");
        }

        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.productPrice = productPrice;
        this.status = StatusEnum.PENDING;
        this.createdAt = LocalDateTime.now();
    }

    // Construtor reservado para reconstruir do banco
    private Order() {}

    // Reconstroi um pedido existente sem disparar validacoes de criacao
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

    private final List<DomainEvent> domainEvents = new ArrayList<>();


    // Regra: total = preco * quantidade com escala 2
    public BigDecimal calculateTotal() {

       return productPrice
               .multiply(BigDecimal.valueOf(quantity))
               .setScale(2, RoundingMode.HALF_UP);
    }

    // Regra: nao pode cancelar pedido confirmado
    public void cancel() {
        if (status == StatusEnum.CONFIRMED) {
            throw new OrderInvalidStateException("Cannot cancel a confirmed order");
        }
        this.status = StatusEnum.CANCELLED;

        domainEvents.add(new OrderCancelled(this.id, this.userId, LocalDateTime.now()));
    }

    // Regra: so pode alterar quantidade em pedidos PENDING
    public void changeQuantity(int newQuantity) {
        // Regra: quantidade deve ser maior que zero
        if (newQuantity <= 0) {
            throw new OrderValidationException("Quantity must be greater than 0");
        }

        if (status == StatusEnum.CANCELLED) {
            throw new OrderInvalidStateException("This order has already been canceled");
        }

        if (status == StatusEnum.CONFIRMED) {
            throw new OrderInvalidStateException("This order is confirmed");
        }

        quantity = newQuantity;
    }

    // Regra: nao pode confirmar pedido cancelado
    public void markAsConfirmed() {
        if (status == StatusEnum.CANCELLED) {
            throw new OrderInvalidStateException("This order has already been canceled");
        }
        status = StatusEnum.CONFIRMED;

        domainEvents.add(new OrderConfirmed(this.id, this.userId, LocalDateTime.now()));

    }

    // Regra: evento de criacao deve ser emitido apos persistencia (id atribuido)
    public void markAsCreated() {
        domainEvents.add(new OrderCreated(
                this.id,
                this.userId,
                this.productId,
                calculateTotal(),
                LocalDateTime.now()
        ));
    }


    // Regra: eventos sao consumidos uma unica vez
    public List<DomainEvent> pullEvents() {
        var events = new ArrayList<>(domainEvents);
        domainEvents.clear();
        return events;
    }



    public void assignId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }


    public StatusEnum getStatus() {
        return status;
    }

    // Retorna quantidade atual
    public int getQuantity() {
        return quantity;
    }


    public Long getUserId() {
        return userId;
    }


    public Long getProductId() {
        return productId;
    }


    public BigDecimal getProductPrice() {
        return productPrice;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public void setId(Long id) {
        this.id = id;
    }

    // Define status do pedido
    public void setStatus(StatusEnum status) {
        this.status = status;
    }


    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
