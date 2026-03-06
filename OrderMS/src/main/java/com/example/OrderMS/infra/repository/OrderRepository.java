package com.example.OrderMS.infra.repository;

import com.example.OrderMS.core.entities.Order;
import com.example.OrderMS.infra.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByUserId(Long id);

}
