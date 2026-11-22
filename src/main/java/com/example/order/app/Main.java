package com.example.order.app;

import com.example.order.processing.Clothing;
import com.example.order.processing.Electronics;
import com.example.order.processing.OrderProcessor;
import com.example.order.processing.Product;
import com.example.order.storage.OrderRepository;
import com.github.javafaker.Faker;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker();

        Electronics laptop = Electronics.builder()
                .name(faker.commerce().productName())
                .price(1111.0)
                .warrantyMonths(12)
                .build();

        Clothing shirt = Clothing.builder()
                .name(faker.commerce().productName())
                .price(2222.0)
                .size("L")
                .build();

        Electronics invalidItem = Electronics.builder()
                .name("Error Gadget")
                .price(-1111.0)
                .warrantyMonths(12)
                .build();

        List<Product> orderList = List.of(laptop, shirt, invalidItem);

        OrderRepository<Product> repository = new OrderRepository<>();
        for (Product item : orderList) {
            if (item.getPrice() > 0) {
                repository.save(item);
            }
        }

        Runnable orderTask = () -> {
            OrderProcessor<Product> processor = new OrderProcessor<>(laptop);
            processor.process();
            System.out.println("Processing order in thread " + Thread.currentThread().getName());
        };
        new Thread(orderTask).start();

        orderList.forEach(OrderProcessor::processOrder);

        System.out.println("\nOnly clothing:");
        repository.filter(p -> p instanceof Clothing).forEach(System.out::println);
    }
}