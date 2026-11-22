package com.example.order.processing;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Clothing extends Product {
    private String size;

    @Builder
    public Clothing(String name, double price, String size) {
        super(name, price);
        this.size = size;
    }

    @Override
    public String toString() {
        return "Clothing(name=" + getName() + ", price=" + getPrice() + ", size=" + size + ")";
    }
}

