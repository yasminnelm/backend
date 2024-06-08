package com.example.backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String codeCreance;
    private String name;
    private double price;

    @ManyToOne
    @JoinColumn(name = "biller_id", nullable = false)
    private Biller biller;
//    @OneToMany(mappedBy = "creance", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Impayes> impayes;
}


