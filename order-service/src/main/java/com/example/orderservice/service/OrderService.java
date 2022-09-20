package com.example.orderservice.service;


import com.example.orderservice.model.Order;
import com.example.orderservice.model.PaymentType;
import com.example.orderservice.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface OrderService {
    Order get(Long id);
    List<Order> getAll();

    String pay(Long orderId);

    List<Product> getProductsOfOrder(Long orderId);

    Order createOrder(Long accountId, List<Product> products);

    Order addProductToOrder(Long orderId, Product product);

    Order addPaymentType(Long orderId, PaymentType paymentType);
    Order deleteOrder(Long orderId);

}
