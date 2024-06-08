package com.example.backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Creancier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String categorieCreancier;
    @OneToMany(mappedBy = "creancier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Creance> creances;

    @OneToMany(mappedBy = "creancier")
    private List<Facture> factures;


    @OneToOne
    private ProfessionalBankAccount professionalBankAccount;

}

