package com.example.bankservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PaymentRequest {
    private Long userId;
    private PaymentType paymentType;
    private Order order;
    private double totalPrice;


}