package com.example.paypalservice.controller;

import com.example.paypalservice.model.PayPalInfo;
import com.example.paypalservice.model.PaymentRequest;
import com.example.paypalservice.service.PayPalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/paypal")
public class PaypalController {
    @Autowired
    private PayPalService payPalService;

    @PostMapping()
    public PayPalInfo addPaypal(@RequestBody PayPalInfo payPalInfo){
       return payPalService.addPaypal(payPalInfo);
    }


    @PostMapping("/process")
    public String processPayment(@RequestBody PaymentRequest request){
        return payPalService.processPayment(request);


    }
}
