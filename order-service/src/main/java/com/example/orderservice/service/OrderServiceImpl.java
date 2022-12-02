package com.example.orderservice.service;
import com.example.orderservice.model.*;

import com.example.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor

public class OrderServiceImpl {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Order get(Long id){
        return orderRepository.findById(id).get();
    }

    public List<Order> getAll(){
        return orderRepository.findAll();
    }



    public Order createOrder(Long accountId, List<OrderProduct> products){
        Order order = new Order(accountId,products);
        // Connect to Account Service and get preferred payment method
        Account userAccount = restTemplate.getForObject("http://account-service:9091/accounts/" + accountId, Account.class);
        if (userAccount== null){
            return null;
        }

        for (OrderProduct product : products){
            System.out.println(product.getProductId());
            Boolean isAvailable = restTemplate.getForObject("http://stock-service:9097/stock/" + product.getProductId(), Boolean.class);
            if (!isAvailable){
                System.out.println("Product not available at the moment .... updating the stock");
                restTemplate.execute("http://stock-service:9097/stock/add/" + product.getProductId(), HttpMethod.POST,null,null);

//                throw new RuntimeException("Product " + product.getProductId() + " is not available");
            }
        }

        order.setPaymentType(userAccount.getPaymentType());
        orderRepository.save(order);
        return order;
    }
//    public Order addProductToOrder(Long orderId, Product product){
//        Optional<Order> optionalOrder = orderRepository.findById(orderId);
//        if(optionalOrder.isEmpty()){
//            System.out.println("No Order Found by id: "+ orderId );
//            throw new RuntimeException("Order " + orderId + " is not available");
//        }
//        Order order = optionalOrder.get();
//        order.addToProductList(product);
//        orderRepository.save(order);
//        return order;
//
//    }

    public Order addPaymentType(Long orderId, PaymentType paymentType) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isPresent()){
            Order order = optionalOrder.get();
            order.setPaymentType(paymentType);
            return orderRepository.save(order);
        }
        throw new RuntimeException("\"No Order Found by id: \"+ orderId ");
    }



    public Order deleteOrder(Long orderId){
        Order order=orderRepository.findById(orderId).get();
        orderRepository.delete(order);
        return order;
    }

    public String pay(Long orderId){
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(!optionalOrder.isPresent()){
            System.out.println("No Order Found by id: "+ orderId );
            return "Order is Not Successful";
        }
        Order order = optionalOrder.get();
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setPaymentType(order.getPaymentType());
        paymentRequest.setOrder(order);
        paymentRequest.setUserId(order.getUserId());
        restTemplate.postForObject("http://payment-service:9094/payments/",paymentRequest, String.class);
        orderRepository.save(order);
        return "Order is successful!! Your payment has been made using "+ paymentRequest.getPaymentType();

    }

}
