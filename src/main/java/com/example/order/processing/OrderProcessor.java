package com.example.order.processing;

import com.example.order.processing.internal.OrderCheckHelper;

public class OrderProcessor<T extends Product> {
    private T product;

    public OrderProcessor(T product) {
        this.product = product;
    }

    public void process() {
        try {
            OrderCheckHelper.validate(product);
            System.out.println("Processing order for: " + product.getName() +
                    " [" + Thread.currentThread().getName() + "]");
        } catch (InvalidOrderException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static <T extends Product> void processOrder(T item) {
        new OrderProcessor<>(item).process();
    }
}