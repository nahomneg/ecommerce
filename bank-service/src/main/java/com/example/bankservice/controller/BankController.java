package com.example.bankservice.controller;

import com.example.bankservice.model.BankInfo;
import com.example.bankservice.model.PaymentRequest;
import com.example.bankservice.service.BankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/bank")
public class BankController {
    @Autowired
    private BankService bankService;

    @PostMapping
    public BankInfo addBankInfo(@RequestBody BankInfo payPalInfo){
       return bankService.addBankInfo(payPalInfo);
    }


    @PostMapping("/process")
    public String processPayment(@RequestBody PaymentRequest request){
        return bankService.processPayment(request);


    }
}
