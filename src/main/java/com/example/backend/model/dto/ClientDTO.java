package com.example.backend.model.dto;

import com.example.backend.model.enumeration.CompteType;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientDTO {
    Long id;
    String nom;
    String prenom;
    String email;
    String telephone;
    String password;
    private byte[] cinRectoPath;
    private byte[] cinVersoPath;
    private boolean firstLogin;
}
