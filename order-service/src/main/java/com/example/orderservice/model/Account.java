package com.example.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Account {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private ShippingAddress shippingAddress;
    private PaymentType paymentType;

}