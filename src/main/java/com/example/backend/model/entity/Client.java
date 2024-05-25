package com.example.backend.model.entity;


import com.example.backend.model.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private String password;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] cinRectoPath;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] cinVersoPath;
    private boolean firstLogin = true;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Roles role = Roles.ROLE_CLIENT;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Compte> comptes;

    public Client(Long id, String nom, String prenom, String email, String telephone,
                  String password, byte[] cinRectoPath, byte[] cinVersoPath, boolean firstLogin) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
        this.cinRectoPath = cinRectoPath;
        this.cinVersoPath = cinVersoPath;
        this.firstLogin = firstLogin;
    }

}