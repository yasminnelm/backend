package com.example.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String uid;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String numCin;
    private String adresse;
    private String telephone;
    private String description;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] cinRectoPath;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] cinVersoPath;
    private String dateNaissance;
    private Long numPatente;
    private Long numRegCom;
    private boolean firstLogin;
}
