package com.example.backend.model.entity;

import com.example.backend.model.enumeration.Roles;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String uid;
    private String lastname;
    private String firstname;
    @Column(unique = true)
    private String email;
    private String password;
    private String numCin;
    private String address;
    private String phonenumber;
    private String description;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] cinRectoPath;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] cinVersoPath;
    private String birthdate;
    private Long numLicence;
    private Long numRegCom;
    private boolean firstLogin = true;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Roles role = Roles.ROLE_AGENT;
}