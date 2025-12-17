package com.example.commandservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String orderId;

    @Column(unique = true)
    private String customerId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column(nullable = false)
    private Instant creationDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderEntity",
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItemEntity> orderItemEntities;


    public void addOrderItem(OrderItemEntity orderItemEntity) {
        if(orderItemEntity.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        orderItemEntities.add(orderItemEntity);
    }

    public void markConfirmed() {
        if(orderItemEntities.isEmpty()) {
            throw new IllegalArgumentException("Order has no order items.");
        }
        this.status = OrderStatus.CONFIRMED;
    }

}
