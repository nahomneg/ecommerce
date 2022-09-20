package com.example.bankservice.service;

import com.example.bankservice.model.BankInfo;
import com.example.bankservice.model.PaymentRequest;
import com.example.bankservice.repository.BankRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BankService {
    @Autowired
    private BankRepository bankRepository;
    public BankInfo addBankInfo(BankInfo bankInfo) {
        return bankRepository.save(bankInfo);

    }

    public String processPayment(PaymentRequest paymentRequest) {
        System.out.println("processed bank payment for :" + paymentRequest.toString());
        return "processed paypal payment for :" + paymentRequest.toString() + " with transaction id : " + UUID.randomUUID();
    }
}
