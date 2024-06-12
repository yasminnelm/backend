package com.example.backend.repository;

import com.example.backend.model.entity.BankAccount;
import com.example.backend.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {


    BankAccount findBankAccountById(Long id);
    @Query("SELECT b FROM BankAccount b WHERE b.client.id = :clientId")
    BankAccount findByClientId(@Param("clientId") Long clientId);

    BankAccount findBankAccountByAccountNumber(Long acccountNumber);
}
