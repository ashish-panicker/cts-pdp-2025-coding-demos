package org.example.orderservice.feignclients;

import org.example.orderservice.dto.ProductResponse;
import org.example.orderservice.dto.ReserveStock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "product-service", url = "http://localhost:9100/api/products")
public interface ProductFeignClient {

    @GetMapping("/{id}")
    ProductResponse getProductById(@PathVariable Long id);

    @PostMapping("/reserve-stock")
    Boolean reserveStock(@RequestBody ReserveStock reserveStock);
}
