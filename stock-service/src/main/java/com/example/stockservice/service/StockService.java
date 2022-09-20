package com.example.stockservice.service;

import com.example.stockservice.model.Stock;
import com.example.stockservice.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public Boolean getAvailability(Long productId){
        Optional<Stock> optionalStock = stockRepository.findByProductId(productId);
        if(optionalStock.isPresent()){
            Stock stock = optionalStock.get();
            return stock.getLeftItems()>0;
        }
        throw new RuntimeException("\"No Product Found by id: "+productId);
    }
    public Stock create(Stock stock){
        return stockRepository.save(stock);
    }

    public String add(Long productId){
        Optional<Stock> optionalStock = stockRepository.findByProductId(productId);
        if(optionalStock.isPresent()){
            Stock stock = optionalStock.get();
            stock.setLeftItems(stock.getLeftItems()+30);
            stockRepository.save(stock);
            return "Stock updated!!";
        }
        throw new RuntimeException("\"No Product Found by id: "+ productId);
    }
    public String deduct(Long productId){
        Optional<Stock> optionalStock = stockRepository.findByProductId(productId);
        if(optionalStock.isPresent()){
            Stock stock = optionalStock.get();
            //mimic the deduction of products from stock
            stock.setLeftItems(stock.getLeftItems()-1);
            stockRepository.save(stock);
            return "Stock updated!!";
        }
        throw new RuntimeException("\"No Order Found by id: \"+ orderId ");
    }
}
