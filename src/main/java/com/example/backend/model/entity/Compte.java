package com.example.backend.model.entity;

import com.example.backend.model.enumeration.CompteType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(nullable = false)
    double plafond;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    CompteType type;

//    @ManyToOne
//    @JoinColumn(name = "client_id", nullable = false)
//    private Client client;
}

