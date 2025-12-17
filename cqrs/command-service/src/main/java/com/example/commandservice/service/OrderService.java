package com.example.commandservice.service;

import com.example.commandservice.kafka.OrderEventPublisher;
import com.example.commandservice.model.OrderEntity;
import com.example.commandservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderEventPublisher orderEventPublisher;

    @Transactional
    public void save(OrderEntity orderEntity) {
        orderRepository.save(orderEntity);
        orderEventPublisher.publishOrderPlaced(orderEntity);
    }
}
