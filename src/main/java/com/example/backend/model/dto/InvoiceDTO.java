package com.example.backend.model.dto;

import com.example.backend.model.enumeration.FactureStatus;
import lombok.Data;


import java.util.Date;

@Data
public class InvoiceDTO {
    private Long id;
    private Long clientId;
    private Long billerId;
    private Double amount;
    private Date issueDate;
    private Date deadline;
    private String referencePaiement;
    private FactureStatus factureStatus;
}
