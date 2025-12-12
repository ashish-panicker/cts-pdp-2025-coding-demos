package org.example.orderservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.orderservice.dto.OrderReserveRequest;
import org.example.orderservice.dto.ReserveStock;
import org.example.orderservice.feignclients.ProductFeignClient;
import org.example.orderservice.model.Order;
import org.example.orderservice.repository.OrderRepository;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository repository;
    private final ProductFeignClient client;


    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id) {
        return buildResponseEntity(repository.getReferenceById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderReserveRequest request) {
        var prod = client.getProductById(request.productId());
        boolean exists = client.reserveStock(new ReserveStock(request.productId(), request.quantity()));
        if (!exists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        var newOrder = Order
                .builder()
                .productId(request.productId())
                .quantity(request.quantity())
                .totalPrice(request.quantity() * prod.price())
                .status("Reserved")
                .build();
        return buildResponseEntity(repository.save(newOrder), HttpStatus.CREATED);
    }

    private <T> ResponseEntity<T> buildResponseEntity(T entity, HttpStatus status) {
        return ResponseEntity.status(status).body(entity);
    }
}
