package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private String id;
    private Customer customer;
    private List<OrderItem> items;
    private double totalAmount;
    private boolean paymentProcessed;

    public Order() {
        items = new ArrayList<>();
    }

    public Order(String id, Customer customer) {
        this();
        this.id = id;
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public boolean isPaymentProcessed() {
        return paymentProcessed;
    }

    public void setPaymentProcessed(boolean paymentProcessed) {
        this.paymentProcessed = paymentProcessed;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }
}
