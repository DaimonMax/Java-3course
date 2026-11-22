package com.example.order.storage;

import com.example.order.processing.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class OrderRepository<T extends Product> {
    private final List<T> orders = new ArrayList<>();

    public synchronized void save(T order) {
        orders.add(order);
    }

    public synchronized List<T> getAll() {
        return new ArrayList<>(orders);
    }

    public synchronized List<T> filter(Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T order : orders) {
            if (predicate.test(order)) {
                result.add(order);
            }
        }
        return result;
    }
}