package com.example.backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("PA")
public class ProfessionalBankAccount extends BankAccount{
    @OneToMany(mappedBy = "professionalBankAccount",fetch = FetchType.LAZY)
    private List<AccountOperation> accountOperationList;
}