package com.example.orderservice.service;


import com.example.orderservice.model.Order;
import com.example.orderservice.model.PaymentType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface OrderService {
    Order get(Long id);
    List<Order> getAll();

    String pay(Long orderId);
    Order addProductById(Long orderId, Long productId);
    Order addPaymentType(Long orderId, PaymentType paymentType);
    Order deleteOrder(Long orderId);

}
