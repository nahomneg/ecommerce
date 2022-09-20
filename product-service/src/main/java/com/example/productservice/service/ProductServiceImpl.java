package com.example.productservice.service;


import com.example.productservice.model.Order;
import com.example.productservice.model.OrderProduct;
import com.example.productservice.model.Product;
import com.example.productservice.model.Stock;
import com.example.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product findProductById(Long id) {
        return productRepository.findProductById(id);
    }

    public Product addProduct(Product product) {

        Product savedProduct = productRepository.save(product);
        Stock stock = new Stock(product.getId(),5);
        restTemplate.postForObject("http://stock-service:9097/stock/create/" + savedProduct.getId(),stock, Order.class);
        return savedProduct;
    }

    public boolean removeProduct(Long productId) {
        Optional<Product> productOptional =productRepository.findById(productId);
        if(productOptional.isPresent()){
            productRepository.deleteById(productId);
            return true;
        }
        return false;
    }

    public Product updateProduct(Long productId, Product productBody) {
        Optional<Product> productOptional= productRepository.findById(productId);
        if(productOptional.isPresent()){
            productBody.setId(productId);
            return productRepository.save(productBody);
        }
        return productRepository.save(productBody);
    }

    public Order makeOrder(Long accountId, List<OrderProduct> products) {
        return restTemplate.postForObject("http://order-service:9093/orders/create/" + accountId ,products, Order.class);

    }

}
