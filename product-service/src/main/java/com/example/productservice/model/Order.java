package com.example.productservice.model;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
public class Order {
    private Long id;

    public Order(Long userId, List<OrderProduct> products) {
        this.userId = userId;
        this.products = products;
    }

    private Long userId;
    private PaymentType paymentType;

    private List<OrderProduct> products = new ArrayList<>();

    public Order() {

    }


}
