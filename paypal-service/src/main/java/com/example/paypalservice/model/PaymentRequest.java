package com.example.paypalservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PaymentRequest implements Serializable {
    private Long userId;
    private PaymentType paymentType;
    private Order order;
    private double totalPrice;


}