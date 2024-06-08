package com.example.backend.model.dto;

import com.example.backend.model.enumeration.Roles;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentDTO {
    private Long id;
    private String uid;
    private String lastname;
    private String firstname;
    private String email;
    private String password;
    private String numCin;
    private String address;
    private String phonenumber;
    private String description;
    private byte[] cinRectoPath;
    private byte[] cinVersoPath;
    private String birthdate;
    private Long numLicence;
    private Long numRegCom;
    private boolean firstLogin;
    private Roles role;
}

