package com.example.backend.model.entity;

import com.example.backend.model.enumeration.AccountStatus;
import com.example.backend.model.enumeration.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//on peut mettre InheritanceType.SINGLE_TABLE pour creer une table pour chaque class
//et dans ce cas on est pas besoin de mettre dans les class filles : //@DiscriminatorValue
//et on me la class a abstract si on ne veut pas creer une table pour la class mere
//@DiscriminatorColumn(name = "TYPE",length = 4)
@Entity

public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double balance;

    private Long accountNumber;

    private Date createdAt;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Enumerated(EnumType.STRING)
    AccountType accountType;

    @OneToMany(mappedBy = "bankAccount",fetch = FetchType.LAZY)
    private List<AccountOperation> accountOperationList;
    //FetchType.LAZY est pour dire a hibernate de ne pas charger la collection lors de la recuperation des infos de compte
    //pour lui dire de les importer aussi on utilise le mode FetchType.EAGER
    //par defaut c'est LAZY pour que la collection ne soit chargé qu'a la demande
//    @OneToMany(mappedBy = "bankAccount",fetch = FetchType.LAZY)
//    private List<AccountOperation> accountOperationList;
    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;
}