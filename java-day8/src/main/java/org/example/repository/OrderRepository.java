package org.example.repository;

import org.example.model.Order;

import java.util.*;

public class OrderRepository {

    private final Map<Integer, Order> store = new HashMap<>();

    public Order save(Order order) {
        store.put(order.getId(), order);
        return order;
    }

    public Optional<Order> findById(int id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Order> getAll() {
        return new ArrayList<>(store.values());
    }

    public void deleteAll(){
        store.clear();
    }
}
