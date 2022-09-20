package com.example.orderservice.model;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Order(Long userId, List<OrderProduct> products) {
        this.userId = userId;
        this.products = products;
    }

    private Long userId;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;


    @OneToMany(cascade = CascadeType.PERSIST)
    List<OrderProduct> products = new ArrayList<>();

    public Order() {

    }

//    public void addToProductList(Product product) {
//        products.add(product);
//    }

}
