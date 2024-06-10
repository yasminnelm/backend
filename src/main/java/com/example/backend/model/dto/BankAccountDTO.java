package com.example.backend.model.dto;

import com.example.backend.model.enumeration.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountDTO {
    private Long id;
    private Long accountNumber;
    private AccountType accountType;
    private double balance;
    private Date createdAt;
    private String status;
    private ClientDTO clientDTO;
}
