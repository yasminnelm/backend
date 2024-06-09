package com.example.backend.repository;

import com.example.backend.model.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    @Query("SELECT b FROM BankAccount b WHERE b.customer.id = :clientId")
    BankAccount findByClientId(@Param("clientId") Long clientId);
}
