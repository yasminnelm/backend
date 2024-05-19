package com.example.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String uid;
    private String nom,prenom,email,password;
   @Lob
   @Column(columnDefinition = "LONGBLOB")
    private byte[] cinRectoPath,cinVersoPath;
    private String numCin,adresse,telephone;
    private Long numPatente,numRegCom;
    boolean firstLogin;
}
