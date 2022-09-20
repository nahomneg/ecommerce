package com.example.paypalservice.repository;

import com.example.paypalservice.model.PayPalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaypalRepository extends JpaRepository<PayPalInfo,Long> {
}
