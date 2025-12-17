package com.example.commandservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order",
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;


    public void addOrderItem(OrderItem orderItem) {
        if(orderItem.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        orderItems.add(orderItem);
    }

    public void markConfirmed() {
        if(orderItems.isEmpty()) {
            throw new IllegalArgumentException("Order has no order items.");
        }
        this.status = OrderStatus.CONFIRMED;
    }

}
