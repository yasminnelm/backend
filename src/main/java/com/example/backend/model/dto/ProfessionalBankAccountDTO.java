package com.example.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionalBankAccountDTO extends BankAccountDTO {
    private List<AccountOperationDTO> accountOperationList;
}
