package com.example.OrderMS.infra.controller;


import com.example.OrderMS.core.entities.Order;
import com.example.OrderMS.core.usecases.cancel.CancelOrderUseCase;
import com.example.OrderMS.core.usecases.confirm.ConfirmOrderUseCase;
import com.example.OrderMS.core.usecases.OrderInput;
import com.example.OrderMS.core.usecases.OrderOutput;
import com.example.OrderMS.core.usecases.create.CreateOrderUseCase;
import com.example.OrderMS.core.usecases.getorder.GetOrderByIdUseCase;
import com.example.OrderMS.infra.controller.dto.CreateOrderRequest;
import com.example.OrderMS.infra.controller.dto.OrderResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.OrderMS.infra.controller.mapper.OrderResponseMapper;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final ConfirmOrderUseCase confirmOrderUseCase;
    private final CancelOrderUseCase cancelOrderUseCase;
    private final GetOrderByIdUseCase getOrderByIdUseCase;
    private  final OrderResponseMapper orderResponseMapper;


    @PostMapping
    public ResponseEntity<OrderOutput> create(@RequestBody @Valid CreateOrderRequest request){
        OrderInput input = new OrderInput(
                request.getUserId(),
                request.getProductId(),
                request.getQuantity(),
                request.getProductPrice()
        );
        OrderOutput output = createOrderUseCase.execute(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(output);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getById(@PathVariable Long id) {
        Order order = getOrderByIdUseCase.execute(id);
        return ResponseEntity.ok(orderResponseMapper.toResponse(order));
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
