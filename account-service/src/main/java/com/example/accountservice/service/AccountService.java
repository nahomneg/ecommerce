package com.example.accountservice.service;

import com.example.accountservice.model.Account;
import com.example.accountservice.model.BankInfo;
import com.example.accountservice.model.PayPalInfo;
import com.example.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AccountRepository accountRepository;
    public BankInfo addBankInfo(BankInfo bankInfo, Long id) {
        return restTemplate.postForObject("http://bank-service:9092/bank/" ,bankInfo, BankInfo.class);
    }
    public PayPalInfo addPayPal(PayPalInfo payPalInfo, Long id) {
        return restTemplate.postForObject("http://paypal-service:9095/paypal/" ,payPalInfo, PayPalInfo.class);
    }
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }
    public Account updateAccount(Long id, Account accountBody) {
        Optional<Account> accountOptional= accountRepository.findById(id);
        if(accountOptional.isPresent()){
            accountBody.setId(id);
            return accountRepository.save(accountBody);
        }
        return accountRepository.save(accountBody);
    }
    public boolean removeAccount(Long accountId) {
        Optional<Account> accountOptional =accountRepository.findById(accountId);
        if(accountOptional.isPresent()){
            accountRepository.deleteById(accountId);
            return true;
        }
        return false;
    }

    public Boolean checkUser(String email, String password) {
        Account account = accountRepository.findAccountByEmail(email);
        if (account == null){
            return false;
        }
        if (account.getPassword().equals(password)){
            return true;
        }
        return false;
    }
}
