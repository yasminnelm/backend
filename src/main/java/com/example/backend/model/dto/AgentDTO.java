package com.example.backend.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentDTO {
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
    private byte[] cinRectoPath;
    private byte[] cinVersoPath;
    private String dateNaissance;
    private Long numPatente;
    private Long numRegCom;
    private boolean firstLogin;
}
