package com.example.OrderMS.infra.controller;


import com.example.OrderMS.core.entities.Order;
import com.example.OrderMS.core.usecases.cancel.CancelOrderUseCase;
import com.example.OrderMS.core.usecases.confirm.ConfirmOrderUseCase;
import com.example.OrderMS.core.usecases.creater.CreateOrderInput;
import com.example.OrderMS.core.usecases.creater.CreateOrderOutput;
import com.example.OrderMS.core.usecases.creater.CreateOrderUseCase;
import com.example.OrderMS.core.usecases.getorder.GetOrderByIdUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final ConfirmOrderUseCase confirmOrderUseCase;
    private final CancelOrderUseCase cancelOrderUseCase;
    private final GetOrderByIdUseCase getOrderByIdUseCase;


    @PostMapping
    public ResponseEntity<CreateOrderOutput> create(@RequestBody @Valid CreateOrderInput input){
        CreateOrderOutput output = createOrderUseCase.execute(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(output);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id) {
        Order order = getOrderByIdUseCase.execute(id);
        return ResponseEntity.ok(order);
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long id){
        cancelOrderUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/confirm")
    public  ResponseEntity<Void> confirmOrder(@PathVariable Long id){
        confirmOrderUseCase.execute(id);
        return ResponseEntity.ok().build();
    }


}
