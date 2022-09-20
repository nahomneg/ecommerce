package com.example.paypalservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayPalInfo {

    @Id
    @GeneratedValue
    private Long id;
    private Long accountId;
    private String emailAddress;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}