package com.example.productservice.service;


import com.example.productservice.model.Order;
import com.example.productservice.model.Product;

import java.util.List;

public interface ProductService  {

    Product findProductById(Long id);

    List<Product> findAllProducts();

    Product addProduct(Product product);

    boolean removeProduct(Long productId);

    Product updateProduct(Long productId, Product product);



    Order makeOrder(Long accountId, List<Product> products);
}
