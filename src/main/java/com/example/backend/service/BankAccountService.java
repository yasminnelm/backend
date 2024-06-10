package com.example.backend.service;


import com.example.backend.model.dto.BankAccountDTO;
import com.example.backend.model.dto.ClientDTO;
import com.example.backend.model.entity.BankAccount;
import com.example.backend.model.entity.Client;
import com.example.backend.model.enumeration.AccountStatus;
import com.example.backend.model.enumeration.AccountType;
import com.example.backend.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;
@Service
public class BankAccountService {
    @Autowired
    BankAccountRepository bankAccountRepository;

    public BankAccount createAccountWithClient(AccountType accountType, Client client) {
        BankAccount bankAccount = new BankAccount();
//        bankAccount.setId(2L);
        bankAccount.setAccountNumber(generateAccountNumber());
        bankAccount.setAccountType(accountType);
        bankAccount.setBalance(0.00);
        bankAccount.setCreatedAt(new Date());
        bankAccount.setStatus(AccountStatus.CREATED);
        bankAccount.setClient(client);
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
        return savedBankAccount;
    }
    private Long generateAccountNumber() {
        Random random = new Random();
        long accountNumber = 10000000000L + (long) (random.nextDouble() * 90000000000L);
        return accountNumber;
    }
}