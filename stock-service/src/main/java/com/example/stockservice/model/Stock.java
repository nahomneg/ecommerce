package com.example.stockservice.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Stock {
    @Id
    @GeneratedValue
    private Long id;

    private Long productId;
    private Integer leftItems;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getLeftItems() {
        return leftItems;
    }

    public void setLeftItems(Integer leftItems) {
        this.leftItems = leftItems;
    }

    public Stock(Long productId, Integer leftItems) {
        this.productId = productId;
        this.leftItems = leftItems;
    }

    public Stock() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
