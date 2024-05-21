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
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String nom;
    String prenom;
    String email;
    String telephone;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] cinRectoPath;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] cinVersoPath;
    @Enumerated(EnumType.STRING)
    @Column(length = 125)
    CompteType compteType;
    private double plafond;

}
