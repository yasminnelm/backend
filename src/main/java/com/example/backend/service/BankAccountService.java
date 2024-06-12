package com.example.backend.service;


import com.example.backend.model.dto.AccountOperationDTO;
import com.example.backend.model.dto.BankAccountDTO;
import com.example.backend.model.dto.ClientDTO;
import com.example.backend.model.entity.AccountOperation;
import com.example.backend.model.entity.BankAccount;
import com.example.backend.model.entity.Client;
import com.example.backend.model.enumeration.AccountStatus;
import com.example.backend.model.enumeration.AccountType;
import com.example.backend.model.enumeration.OperationType;
import com.example.backend.model.mapper.AccountOperationMapper;
import com.example.backend.model.mapper.BankAccountMapper;
import com.example.backend.repository.AccountOperationRepository;
import com.example.backend.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class BankAccountService {
    @Autowired
    BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountOperationRepository accountOperationRepository;
    private final BankAccountMapper bankAccountMapper = BankAccountMapper.INSTANCE;
    private final AccountOperationMapper accountOperationMapper = AccountOperationMapper.INSTANCE;



    public BankAccount findBackAccount(Long id) {
        return bankAccountRepository.findBankAccountById(id);
    }
    public BankAccountDTO findBankAccountByClient(Client client) {
        return BankAccountMapper.INSTANCE.toDto(bankAccountRepository.findByClientId(client.getId()));
    }

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
    //debit credit trasferer
    public boolean debit(BankAccount account, double amount) {
        if (account == null) {
            throw new IllegalArgumentException("Invalid bank account.");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("Insufficient amount.");
        }

//        double balance = CMIService.getBalance(account.getAccountNumber());
        double balance = account.getBalance();
        if (balance < amount) {
            throw new IllegalArgumentException("Insufficient amount .");
        }
        account.setBalance(balance - amount);
        bankAccountRepository.save(account);

        return true;
    }
    public boolean credit(BankAccount account, double amount) {
        if (account == null) {
            throw new IllegalArgumentException("Invalid bank account.");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("the amount must be positif.");
        }

        double maxAmount;
        switch (account.getAccountType()) {
            case COMPTE_200:
                maxAmount = 200;
                break;
            case COMPTE_5000:
                maxAmount = 5000;
                break;
            case COMPTE_20000:
                maxAmount = 20000;
                break;
            case COMPTE_PROFESSIONNEL:
                maxAmount = Double.MAX_VALUE;
                break;
            default:
                throw new IllegalArgumentException("unknown account type");
        }

        if (amount > maxAmount) {
            throw new IllegalArgumentException("The amount to be credited exceeds the authorized ceiling for this type of account.");
        }
        account.setBalance(account.getBalance() + amount);
        bankAccountRepository.save(account);

        return true;
    }
    @Transactional
    public void transfer(Long sourceAccountNumber, Long destinationAccountNumber, double amount) {
        BankAccount sourceAccount = bankAccountRepository.findBankAccountByAccountNumber(sourceAccountNumber);
              if(sourceAccount==null) {
                  throw new IllegalArgumentException("Source account not found.");
              }
              BankAccount destinationAccount = bankAccountRepository.findBankAccountByAccountNumber(destinationAccountNumber);
        if(destinationAccount==null) {
            throw new IllegalArgumentException("Destination account not found.");
        }
        debit(sourceAccount, amount);
        credit(destinationAccount, amount);
        Client sourceClient = sourceAccount.getClient();
        Client destinationClient = destinationAccount.getClient();

        String sourceClientName = sourceClient.getFirstname() + " " + sourceClient.getLastname();
        String destinationClientName = destinationClient.getFirstname() + " " + destinationClient.getLastname();

        //Debit operation
        AccountOperation debitOperation = new AccountOperation();
        debitOperation.setOperationDate(new Date());
        debitOperation.setDescription("Transfer to " + destinationClientName);
        debitOperation.setAmount(amount);
        debitOperation.setType(OperationType.DEBIT);
        debitOperation.setBankAccount(sourceAccount);
        accountOperationRepository.save(debitOperation);

        //Credit operation
        AccountOperation creditOperation = new AccountOperation();
        creditOperation.setOperationDate(new Date());
        creditOperation.setDescription("Transfer from " + sourceClientName);
        creditOperation.setAmount(amount);
        creditOperation.setType(OperationType.CREDIT);
        creditOperation.setBankAccount(destinationAccount);
        accountOperationRepository.save(creditOperation);
    }
    public BankAccount editBankAccount(BankAccount bankAccount1) {
        BankAccount bankAccount = bankAccountRepository.findById(bankAccount1.getId()).orElseThrow(() -> new IllegalArgumentException("Account not found"));

        bankAccount.setAccountNumber(bankAccount1.getAccountNumber());
        bankAccount.setAccountType(bankAccount1.getAccountType());
        bankAccount.setBalance(bankAccount1.getBalance());
        bankAccount.setStatus(bankAccount1.getStatus());

        return bankAccountRepository.save(bankAccount);
    }

    public List<AccountOperationDTO> getAccountOperations(Long accountId) {
        BankAccount bankAccount = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found."));
        List<AccountOperation> operations = accountOperationRepository.findByBankAccount(bankAccount);
        return operations.stream().map(accountOperationMapper::toDto).collect(Collectors.toList());
    }
}