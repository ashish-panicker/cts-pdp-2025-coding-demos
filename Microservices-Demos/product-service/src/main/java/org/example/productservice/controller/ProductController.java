package org.example.productservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.productservice.model.Product;
import org.example.productservice.repository.ProductRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    /*
     * | Method     | Endpoint                                       | Description           | Input                | Output          |
     * | ---------- | ---------------------------------------------- | --------------------- | -------------------- | --------------- |
     * | **GET**    | `/products/{id}`                               | Fetch a product by ID | Path variable (`id`) | Product JSON    |
     * | **POST**   | `/products` *(optional CRUD)*                  | Create a new product  | Product JSON         | Saved Product   |
     * | **PUT**    | `/products/{id}` *(optional)*                  | Update product        | Product JSON         | Updated Product |
     * | **DELETE** | `/products/{id}` *(optional)*                  | Delete product        | Path variable        | void            |
     * | **POST**   | `/products/reserve-stock?productId=&quantity=` | Check + reserve stock | Request params       | `true/false`    |
     */

    private final ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return buildResponseEntity(productRepository.save(product), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return buildResponseEntity(productRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        log.info("REST request to get product : {}", id);
        return buildResponseEntity(productRepository.findById(id).orElseThrow(), HttpStatus.OK);
    }

    @PostMapping("/reserve-stock")
    public ResponseEntity<Boolean> reserveStock(@RequestBody ReserveStock request) {

        var product = productRepository.findById(request.productId()).orElseThrow();
        log.info("product id:{}, quantity:{}", request.productId(), request.quantity());
        log.info("product {}", product);
        if (product.getStock() < request.quantity()) {
            return buildResponseEntity(false, HttpStatus.BAD_REQUEST);
        }
        product.setStock(product.getStock() - request.quantity());
        log.info("product - {}", product);
        productRepository.save(product);
        return buildResponseEntity(true, HttpStatus.OK);
    }

    private <T> ResponseEntity<T> buildResponseEntity(T entity, HttpStatus status) {
        return ResponseEntity.status(status).body(entity);
    }

}
