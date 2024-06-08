package com.example.backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Creance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String codeCreance;
    private String nom;
    private double prix;

    @ManyToOne
    @JoinColumn(name = "creancier_id", nullable = false)
    private Creancier creancier;
//    @OneToMany(mappedBy = "creance", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Impayes> impayes;
}



