package org.example.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
public class Order {

    private final int id;
    private final List<Item> items;
    private double total;

    public Order(int id, List<Item> items) {
        this.id = id;
        this.items = items;
        this.total = calculateTotal();
    }

    public void applyDiscount(double discount) {
        this.total -= discount;
    }

    public double calculateTotal() {
        return items.stream().mapToDouble(i -> i.price() * i.quantity()).sum();
    }
}
