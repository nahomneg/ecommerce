package com.example.orderservice.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class OrderProduct {

    private Long productId;
    private Integer quantity;
    @Id
    @GeneratedValue
    private Long id;
}
