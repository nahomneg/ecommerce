package com.example.paypalservice.service;

import com.example.paypalservice.model.PayPalInfo;
import com.example.paypalservice.model.PaymentRequest;
import com.example.paypalservice.repository.PaypalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PayPalService {
    @Autowired
    private PaypalRepository paypalRepository;
    public PayPalInfo addPaypal(PayPalInfo payPalInfo) {
        return paypalRepository.save(payPalInfo);

    }

    public String processPayment(PaymentRequest paymentRequest) {
        System.out.println("processed paypal payment for :" + paymentRequest.toString());
        return "processed paypal payment for :" + paymentRequest.toString() + " with transaction id : " + UUID.randomUUID();
    }
}
