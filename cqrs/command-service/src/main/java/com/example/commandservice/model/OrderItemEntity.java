package com.example.commandservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_itemss")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity orderEntity;

    @Column(nullable = false)
    private String productId;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;

    public OrderItemEntity(String productId, Integer quantity, Double price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }
}
