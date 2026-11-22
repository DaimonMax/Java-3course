package com.example.order.processing;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Electronics extends Product {
    private int warrantyMonths;

    @Builder
    public Electronics(String name, double price, int warrantyMonths) {
        super(name, price);
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public String toString() {
        return "Electronics(name=" + getName() + ", price=" + getPrice() + ", warrantyMonths=" + warrantyMonths + ")";
    }
}