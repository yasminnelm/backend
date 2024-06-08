package com.example.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientBankAccountDTO extends BankAccountDTO {
    private String accountType;
    private ClientDTO customer;
}
