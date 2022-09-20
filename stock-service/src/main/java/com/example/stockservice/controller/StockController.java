package com.example.stockservice.controller;

import com.example.stockservice.model.Stock;
import com.example.stockservice.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private StockService stockService;
    @GetMapping("/{productId}")
    public Boolean getAvailability(@PathVariable Long productId){
        return stockService.getAvailability(productId);
    }
    @PostMapping("/add/{productId}")
    public String add(@PathVariable Long productId){
        return stockService.add(productId);
    }
    @PostMapping("/create/{productId}")
    public Stock create(@RequestBody Stock stock){
        return stockService.create(stock);
    }
    @PutMapping("/deduct/{productId}")
    public String deduct(@PathVariable Long productId){
        return stockService.deduct(productId);
    }
}
