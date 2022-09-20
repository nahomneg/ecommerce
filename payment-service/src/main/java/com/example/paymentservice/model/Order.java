package com.example.paymentservice.model;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
public class Order {

    private Long id;

    public Order(Long userId, List<Product> products) {
        this.userId = userId;
        this.products = products;
    }

    private Long userId;
    private PaymentType paymentType;


    List<Product> products = new ArrayList<>();

    public Order() {

    }

    public void addToProductList(Product product) {
        products.add(product);
    }

}
