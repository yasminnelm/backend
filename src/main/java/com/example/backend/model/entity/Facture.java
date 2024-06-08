package com.example.backend.model.entity;

import com.example.backend.model.enumeration.FactureStatus;
import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;

@Entity
@Data
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "creancier_id")
    private Creancier creancier;

    private Double montant;
    private Date dateEmission;
    private Date dateEcheance;
    private String referencePaiement;

    @Enumerated(EnumType.STRING)
    private FactureStatus factureStatus;


}

