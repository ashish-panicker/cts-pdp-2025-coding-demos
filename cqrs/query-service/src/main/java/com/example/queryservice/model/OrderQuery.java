package com.example.queryservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document(collection = "order_query")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderQuery {

    @Id
    private String orderId;
    private String customerId;
    private String eventId;
    private Instant eventTime;
    private OrderStatusQuery status;
    private List<OrderItemQuery> orderItems;
}
