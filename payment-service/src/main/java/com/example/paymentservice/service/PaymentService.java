package com.example.paymentservice.service;

import com.example.paymentservice.model.PaymentRequest;
import com.example.paymentservice.model.PaymentType;
import com.example.paymentservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private RestTemplate restTemplate;

    public String processPayment(@RequestBody PaymentRequest paymentRequest){
        PaymentType paymentType = paymentRequest.getPaymentType();
        List<Product> productList = paymentRequest.getOrder().getProducts();
        double totalPrice = 0;
        for (Product product : productList){
            totalPrice += product.getPrice();
        }
        paymentRequest.setTotalPrice(totalPrice);
        if(paymentType.equals(PaymentType.CC)){
            restTemplate.postForObject("http://credit-card-service/credit/" ,paymentRequest, String.class);
        }
        else if(paymentType.equals(PaymentType.PAYPAL)){
            restTemplate.postForObject("http://paypal-service:9095/paypal/" ,paymentRequest, String.class);
        }
        else if(paymentType.equals(PaymentType.BANK)){
            restTemplate.postForObject("http://bank-service:9092/bank/" ,paymentRequest, String.class);
        }
        else{
            return "Please set payment type";
        }
        return "Payment was successful... starting shipment";

    }
}
