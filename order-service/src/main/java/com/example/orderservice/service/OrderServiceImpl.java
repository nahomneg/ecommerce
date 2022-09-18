package com.example.orderservice.service;
import com.example.orderservice.model.Order;

import com.example.orderservice.model.PaymentType;
import com.example.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor

public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Order get(Long id){
        return orderRepository.findById(id).get();
    }

    @Override
    public List<Order> getAll(){
        return orderRepository.findAll();
    }






    @Override
    public Order addProductById(Long orderId,Long productId ) {
//        Optional<Order> optionalOrder = orderRepository.findById(orderId);
//        if(!optionalOrder.isPresent()){
//            System.out.println("No Order Found by id: "+ orderId );
//            return null;
//        }
//        Order order = optionalOrder.get();
//        if(order.getStatus().equals(Status.DRAFT)){
//            ProductDTO product1 = restTemplate.getForObject("http://localhost:9021/products/" + product.getProductId(), ProductDTO.class);
//
//            if(product1.isInStock()) {
//                Product product2 = new Product();
//                product2.setProductId(product.getProductId());
//                productRepository.save(product2);
//                order.addToProductList(product2);
//                return orderRepository.save(order);
//            } else {
//                System.out.println("Product out of stock");
//                return null;
//            }
//        }else {
//            System.out.println("Order status already changed");
//            return null;
//        }
        return null;
    }

    @Override
    public Order addPaymentType(Long orderId, PaymentType paymentType) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isPresent()){
            Order order = optionalOrder.get();
            order.setPaymentType(paymentType);
            return orderRepository.save(order);
        }
        System.out.println("No Order Found by id: "+ orderId );
        return null;
    }

    @Override
    public Order updateStatus(Long orderId, Status status){
        Order order = orderRepository.findById(orderId).get();
        order.setStatus(status);
        return order;
    }

    @Override
    public Order deleteOrder(Long orderId){
        Order order=orderRepository.findById(orderId).get();
        orderRepository.delete(order);
        return order;
    }


    @Override
    public String pay(Long orderId){
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(!optionalOrder.isPresent()){
            System.out.println("No Order Found by id: "+ orderId );
            return "Order is Not Successful";
        }
        Order order = optionalOrder.get();
        order.setStatus(Status.PENDING);
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setPaymentType(order.getPaymentType());
        paymentRequest.setOrderId(order.getId().toString());
        paymentRequest.setUserId(order.getUserId());
        restTemplate.postForObject("http://localhost:9031/payments/" + order.getPaymentType(),paymentRequest, String.class);
        order.setStatus(Status.SHIPPED);
        orderRepository.save(order);
        return "Order is Successful!!";
//        Order order=orderRepository.findById(orderId).get();
//        PaymentRequest request = new PaymentRequest();
////        request.setUserId(order.getUserId());
////        request.setOrderId(orderId.toString());

//        request.setPaymentType(PaymentType.valueOf(paymentType));

//        Double totalPrice = 0.0;
//        log.info(productService.getProductUri());
//        for (Orderline orderline : order.getOrderlineList() ) {
//            Product product = restTemplate.getForObject(productService.getProductUri()+p.getProductId(), Product.class);
//            totalPrice += product.getPrice() * .getQuantity();
//        }
//        request.setBalance(totalPrice);




//        return order;
    }

}
