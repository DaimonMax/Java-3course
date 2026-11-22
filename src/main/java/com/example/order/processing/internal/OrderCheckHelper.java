package com.example.order.processing.internal;

import com.example.order.processing.InvalidOrderException;
import com.example.order.processing.Product;

public class OrderCheckHelper {
    public static <T extends Product> void validate(T product) throws InvalidOrderException {
        if (product == null || product.getPrice() <= 0) {
            throw new InvalidOrderException("Invalid product or price: " + product);
        }
    }
}