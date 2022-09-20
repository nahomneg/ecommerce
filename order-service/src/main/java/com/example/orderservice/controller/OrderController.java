package com.example.orderservice.controller;

import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderProduct;
import com.example.orderservice.model.PaymentType;
import com.example.orderservice.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping()
    public ResponseEntity<List<Order>> getOrders() {
        return new ResponseEntity<>(orderService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.get(id), HttpStatus.OK);
    }
    @PostMapping("/create/{id}")
    public ResponseEntity<Order> createOrder(@RequestBody List<OrderProduct> products, @PathVariable Long id ) {
        return new ResponseEntity<>(orderService.createOrder(id, products), HttpStatus.OK);
    }
//    @PostMapping("/addProductToOrder/{orderId}")
//    public ResponseEntity<?> addProductToOrder(@RequestBody Product product, @PathVariable Long orderId) {
//        Order order=orderService.addProductToOrder(orderId,product);
//        if(order == null) return new ResponseEntity<>("Order Not Found", HttpStatus.BAD_REQUEST);
//        return new ResponseEntity<>(order, HttpStatus.OK);
//    }
    @PutMapping("/addPaymentType/{orderId}")
    public ResponseEntity<?> addPaymentTypeToOrder(@RequestParam PaymentType paymentType, @PathVariable Long orderId) {
        Order order=orderService.addPaymentType(orderId,paymentType);
        if(order == null) return new ResponseEntity<>("Order Not Found", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    @PostMapping("/{orderId}/pay")
    public ResponseEntity<?> pay(@PathVariable Long orderId) {
        String result = orderService.pay(orderId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @DeleteMapping("/{orderid}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderid){
        return new ResponseEntity<Object>(orderService.deleteOrder(orderid), HttpStatus.OK);
    }
}
