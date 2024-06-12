package com.example.backend.model.entity;

import com.example.backend.model.enumeration.InvoiceStatus;
import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;

@Entity
@Data
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "biller_id")
    private Biller biller;

    private Double amount;
    private Date issueDate;
    private Date deadline;
    private Long referencePayment;
    @JoinColumn(name = "description")
    private String description;


    @Enumerated(EnumType.STRING)
    private InvoiceStatus invoiceStatus;


}
