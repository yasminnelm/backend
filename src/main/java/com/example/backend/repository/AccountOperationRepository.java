package com.example.backend.repository;

import com.example.backend.model.entity.AccountOperation;
import com.example.backend.model.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountOperationRepository extends JpaRepository<AccountOperation, Long> {
    List<AccountOperation> findByBankAccount(BankAccount bankAccount);
}
