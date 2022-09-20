package com.example.bankservice.repository;

import com.example.bankservice.model.BankInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<BankInfo,Long> {
}
