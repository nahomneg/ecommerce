package com.example.productservice.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class OrderProduct {

    private Long productId;
    @Id
    @GeneratedValue
    private Long id;
    private Integer quantity;
}
