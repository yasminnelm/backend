package com.example.backend.model.dto;

import com.example.backend.model.enumeration.FactureStatus;
import lombok.Data;


import java.util.Date;

@Data
public class FactureDTO {
    private Long id;
    private Long clientId;
    private Long creancierId;
    private Double montant;
    private Date dateEmission;
    private Date dateEcheance;
    private String referencePaiement;
    private FactureStatus factureStatus;
}