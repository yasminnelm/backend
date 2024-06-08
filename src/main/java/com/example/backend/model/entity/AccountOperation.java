package com.example.backend.model.entity;

import com.example.backend.model.enumeration.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

//cette classe est pour enregistrer les opérations de compte
public class AccountOperation {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date operationDate;
    private double amount;
    @Enumerated(EnumType.STRING)
    private OperationType type;
    @ManyToOne
    private BankAccount bankAccount;
    private String description;
}