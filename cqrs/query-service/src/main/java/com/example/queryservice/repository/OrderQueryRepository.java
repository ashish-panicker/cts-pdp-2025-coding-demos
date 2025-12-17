package com.example.queryservice.repository;

import com.example.queryservice.model.OrderQuery;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderQueryRepository extends MongoRepository<OrderQuery, String> {
}
