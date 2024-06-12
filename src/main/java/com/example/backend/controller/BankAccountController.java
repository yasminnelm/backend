package com.example.backend.controller;

import com.example.backend.model.dto.AccountOperationDTO;
import com.example.backend.model.entity.AccountOperation;
import com.example.backend.model.entity.BankAccount;
import com.example.backend.model.entity.Biller;
import com.example.backend.model.enumeration.OperationType;
import com.example.backend.model.utils.Form;
import com.example.backend.repository.AccountOperationRepository;
import com.example.backend.repository.BankAccountRepository;
import com.example.backend.repository.BillerRepository;
import com.example.backend.service.BankAccountService;
import com.example.backend.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@PreAuthorize("hasAuthority('ROLE_CLIENT')")
@RequestMapping("/api/bankaccount")
public class BankAccountController {

    @Autowired
    private BillerRepository billerRepository;
    @Autowired
    BankAccountService bankAccountService;
    @Autowired
    private FormService formService;
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountOperationRepository accountOperationRepository;


    @GetMapping("/listecreanciers")
    public List<Biller> getAllBillers() {
        return billerRepository.findAll();
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transferMoney(@RequestParam Long sourceAccountNumber,
                                           @RequestParam Long destinationAccountNumber,
                                           @RequestParam double amount) {
        try {
            bankAccountService.transfer(sourceAccountNumber, destinationAccountNumber, amount);
            Map<String, String> response = new HashMap<>();
            response.put("message", "transfer done successfully");

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Internal server error: " + e.getMessage());
        }
    }
    @PostMapping("/payment")
    public ResponseEntity<?> payment(@RequestParam Long sourceAccountId,
                                           @RequestParam double amount) {
        try {
            BankAccount bankAccount = bankAccountService.findBackAccount(sourceAccountId);
            bankAccountService.debit(bankAccount, amount);
//            return ResponseEntity.ok("Payment completed successfully");
            Map<String, String> response = new HashMap<>();
            response.put("message", "payment done successfully");
            //Debit operation
            AccountOperation debitOperation = new AccountOperation();
            debitOperation.setOperationDate(new Date());
            debitOperation.setDescription("invoice payment ");
            debitOperation.setAmount(amount);
            debitOperation.setType(OperationType.DEBIT);
            debitOperation.setBankAccount(bankAccountRepository.getReferenceById(sourceAccountId));
            accountOperationRepository.save(debitOperation);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Internal server error: " + e.getMessage());
        }
    }

    @GetMapping("/operations")
    public ResponseEntity<?> getAccountOperations(@RequestParam Long accountId) {
        try {
            List<AccountOperationDTO> operations = bankAccountService.getAccountOperations(accountId);
            return ResponseEntity.ok(operations);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Internal server error: " + e.getMessage());
        }
    }

    @GetMapping("/forms/{type}")
    public Form getFormByType(@PathVariable String type) {
        return formService.getFormByBillCategory(type);
    }

}
