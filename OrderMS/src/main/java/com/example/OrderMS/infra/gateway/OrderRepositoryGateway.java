package com.example.OrderMS.infra.gateway;

import com.example.OrderMS.core.entities.Order;
import com.example.OrderMS.core.gateways.OrderGateway;
import com.example.OrderMS.infra.mapper.OrderMapper;
import com.example.OrderMS.infra.model.OrderEntity;
import com.example.OrderMS.infra.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class OrderRepositoryGateway implements OrderGateway {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;


    @Override
    public Order save(Order order) {
        OrderEntity entity = orderMapper.toEntity(order);
        OrderEntity savedEntity = orderRepository.save(entity);
        return orderMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Order> findById(Long id) {

        return orderRepository.findById(id)
                .map(orderMapper::toDomain);
    }

    @Override
    public List<Order> findByUserId(Long id) {
        return orderRepository.findByUserId(id)
                .stream()
                .map(entity -> orderMapper.toDomain(entity))
                .toList();
    }
}

