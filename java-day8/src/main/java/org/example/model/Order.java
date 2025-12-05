package org.example.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Order {

    private final int id;
    private final List<Item> items;
    private double total;

    public void applyDiscount(double discount) {
        this.total -= discount;
    }

    public double calculateTotal() {
        this.total =  items.stream().mapToDouble(i -> i.price() * i.quantity()).sum();
        return this.total;
    }
}
