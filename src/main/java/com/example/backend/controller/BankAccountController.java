package com.example.backend.controller;

import com.example.backend.model.dto.AccountOperationDTO;
import com.example.backend.model.entity.Biller;
import com.example.backend.model.utils.Form;
import com.example.backend.repository.BillerRepository;
import com.example.backend.service.BankAccountService;
import com.example.backend.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping("/listecreanciers")
    public List<Biller> getAllBillers() {
        return billerRepository.findAll();
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transferMoney(@RequestParam Long sourceAccountId,
                                           @RequestParam Long destinationAccountId,
                                           @RequestParam double amount) {
        try {
            bankAccountService.transfer(sourceAccountId, destinationAccountId, amount);
            return ResponseEntity.ok("Transfer completed successfully");
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
