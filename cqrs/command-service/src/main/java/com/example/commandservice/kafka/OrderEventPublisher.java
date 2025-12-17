package com.example.commandservice.kafka;

import com.example.commandservice.model.OrderEntity;
import com.example.commandservice.model.OrderItemEntity;
import com.example.order.avro.OrderItem;
import com.example.order.avro.OrderStatus;
import com.example.order.avro.events.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String TOPIC = "order-command-events";

    public void publishOrderPlaced(OrderEntity orderEntity) {
        OrderCreatedEvent event = new OrderCreatedEvent();
        event.setOrderId(orderEntity.getOrderId());
        event.setEventId(UUID.randomUUID().toString());
        event.setEventTime(Instant.now());
        event.setStatus(OrderStatus.PLACED);
        event.setCustomerId(orderEntity.getCustomerId());
        event.setOrderItems(orderEntity.getOrderItemEntities().stream()
                        .map(this::mapItem)
                        .toList());

        kafkaTemplate.send(TOPIC, orderEntity.getOrderId(), event);
    }

    private OrderItem mapItem(OrderItemEntity entity) {
        return OrderItem.newBuilder()
                .setProductId(entity.getProductId())
                .setQuantity(entity.getQuantity())
                .setPrice(entity.getPrice())
                .build();
    }

}
