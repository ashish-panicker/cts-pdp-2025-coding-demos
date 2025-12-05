package org.example.service;

import org.example.model.Item;
import org.example.model.Order;
import org.example.repository.OrderRepository;
import org.example.util.DiscountUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    OrderRepository repo;
    DiscountUtil util;
    OrderService service;
    Order order1, order2;

    @BeforeEach
    public void setUp() {
        repo = new OrderRepository();
        util = new DiscountUtil();
        service = new OrderService(repo, util);
        order1 = new Order(1, List.of(new Item("Coffee mug", 300, 5)));
        order2 = new Order(2, List.of(new Item("Coffee maker", 3000, 2)));
    }

    @Test
    @DisplayName("Create order with discount")
    void createOrder_validAmount_appliesDiscountAndSaveOrder() {
        var saved = service.createOrder(order2);
        assertNotNull(saved);
        assertTrue(repo.findById(2).isPresent());
        assertEquals(1200, util.calculateDiscount(6000), 0.001);
        assertEquals(4800, saved.getTotal(), 0.001);
    }

}