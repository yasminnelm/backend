package com.example.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientDTO {
    Long id;
    String lastname;
    String firstname;
    String email;
    String phonenumber;
    String password;
    private byte[] cinRectoPath;
    private byte[] cinVersoPath;
    private boolean firstLogin;
    private BankAccountDTO bankAccountDTO;
}
