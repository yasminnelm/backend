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
public class Biller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String billCategory;
    @OneToMany(mappedBy = "biller",fetch = FetchType.LAZY)
    private List<Bill> bills;

    @OneToMany(mappedBy = "biller", fetch = FetchType.LAZY)
    private List<Invoice> invoices;
}

