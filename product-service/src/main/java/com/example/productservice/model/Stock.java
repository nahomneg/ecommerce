package com.example.productservice.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
public class Stock {

    private Long id;

    private Long productId;
    private Integer leftItems;



    public Stock(Long productId, Integer leftItems) {
        this.productId = productId;
        this.leftItems = leftItems;
    }

    public Stock() {

    }

}
