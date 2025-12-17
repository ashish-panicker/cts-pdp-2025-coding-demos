package com.example.queryservice.kafka;

import com.example.order.avro.OrderItem;
import com.example.order.avro.events.OrderCreatedEvent;
import com.example.queryservice.model.OrderItemQuery;
import com.example.queryservice.model.OrderQuery;
import com.example.queryservice.model.OrderStatusQuery;
import com.example.queryservice.repository.OrderQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderEventConsumer {

    private final OrderQueryRepository orderQueryRepository;

    @KafkaListener(topics = "order-command-events", groupId = "order-query-grp")
    public void handleOrderCreatedEvent(OrderCreatedEvent event) {
        log.info("Received OrderCreatedEvent {}", event);
        var orderQuery = OrderQuery.builder()
                .orderId(event.getOrderId())
                .eventId(event.getEventId())
                .customerId(event.getCustomerId())
                .eventTime(event.getEventTime())
                .status(OrderStatusQuery.valueOf(event.getStatus().toString()))
                .orderItems(event.getOrderItems().stream().map(this::mapItem).toList())
                .build();
        orderQueryRepository.save(orderQuery);
    }

    private OrderItemQuery mapItem(OrderItem orderItem) {
        return OrderItemQuery.builder()
                .price(orderItem.getPrice())
                .quantity(orderItem.getQuantity())
                .productId(orderItem.getProductId())
                .build();
    }
}
