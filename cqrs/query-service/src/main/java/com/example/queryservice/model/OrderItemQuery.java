package com.example.queryservice.model;

import lombok.*;

@AllArgsConstructor
@Getter
@Builder
@Setter
@NoArgsConstructor
public class OrderItemQuery {

    private String productId;
    private Integer quantity;
    private Double price;
}
