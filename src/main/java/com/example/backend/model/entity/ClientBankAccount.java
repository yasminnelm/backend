package com.example.backend.model.entity;

import com.example.backend.model.enumeration.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("CA")
public class ClientBankAccount extends BankAccount{
      @Enumerated(EnumType.STRING)
      AccountType accountType;
      @OneToOne
      private Client customer;
}
