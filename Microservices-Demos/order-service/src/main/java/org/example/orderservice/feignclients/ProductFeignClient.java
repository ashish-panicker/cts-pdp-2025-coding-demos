package org.example.orderservice.feignclients;

import org.example.orderservice.config.FeignConfig;
import org.example.orderservice.dto.ProductResponse;
import org.example.orderservice.dto.ReserveStock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "product-service")
public interface ProductFeignClient {

    @GetMapping("/api/products/{id}")
    ProductResponse getProductById(@PathVariable Long id);

    @PostMapping("/api/products/reserve-stock")
    Boolean reserveStock(@RequestBody ReserveStock reserveStock);
}
