package com.api.payments.services.impl;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Order {

    // construtor
    public Order(int i, double v, OrderStatus orderStatus) {
        this.id = i;
        this.totalAmount = v;
        this.status = orderStatus;
    }

    enum OrderStatus {
        PENDING,
        SHIPPED,
        DELIVERED,
        CANCELLED
    }

    public int id;
    public double totalAmount;
    private OrderStatus status;
}
