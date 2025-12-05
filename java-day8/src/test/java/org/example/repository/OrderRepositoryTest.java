package org.example.repository;

import org.example.model.Item;
import org.example.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderRepositoryTest {

    OrderRepository repo;
    Order order1, order2;

    @BeforeEach
    public void setUp() {
        repo = new OrderRepository();
        order1 = new Order(1, List.of(new Item("Coffee mug", 300, 5)));
        order2 = new Order(2, List.of(new Item("Coffee maker", 3000, 2)));
    }

    // snake_case:  methoUnderTest_condition_expectedOutcome
    @Test
    @DisplayName("Save valid order")
    void save_withValidOrder_createOrder() {
        repo.save(order1);
        repo.save(order2);
        assertTrue(repo.findById(1).isPresent());
        assertTrue(repo.findById(2).isPresent());
    }


    @Test
    @DisplayName("Find all orders")
    void getAll_valid_returnOrderList() {
        repo.save(order1);
        repo.save(order2);
        assertEquals(2, repo.getAll().size());
    }

    @Test
    @DisplayName("Delete all orders")
    void deleteAll_valid_ClearOrderList() {
        repo.save(order1);
        repo.save(order2);
        repo.deleteAll();
        assertEquals(0, repo.getAll().size());
    }
}