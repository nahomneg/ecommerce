package com.example.productservice.service;


import com.example.productservice.model.Order;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findProductById(id);
    }

    @Override
    public Product addProduct(Product product) {

        return productRepository.save(product);
    }

    @Override
    public boolean removeProduct(Long productId) {
        Optional<Product> productOptional =productRepository.findById(productId);
        if(productOptional.isPresent()){
            productRepository.deleteById(productId);
            return true;
        }
        return false;
    }

    @Override
    public Product updateProduct(Long productId, Product productBody) {
        Optional<Product> productOptional= productRepository.findById(productId);
        if(productOptional.isPresent()){
            productBody.setId(productId);
            return productRepository.save(productBody);
        }
        return productRepository.save(productBody);
    }

    @Override
    public Order makeOrder(Long accountId, List<Product> products) {
        return restTemplate.postForObject("http://localhost:9093/orders/create/" + accountId ,products, Order.class);

    }

}
