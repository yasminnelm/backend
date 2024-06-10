package com.example.backend.model.entity;


import com.example.backend.model.enumeration.Roles;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String lastname;

    String firstname;

    String email;

    String phonenumber;

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

    @OneToOne

    @JoinColumn(name = "bankAccount_id")
    BankAccount bankAccount;

    public Client(Long id, String lastname, String firstname, String email, String phonenumber,
                  String password, byte[] cinRectoPath, byte[] cinVersoPath, boolean firstLogin) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.phonenumber = phonenumber;
        this.password = password;
        this.cinRectoPath = cinRectoPath;
        this.cinVersoPath = cinVersoPath;
        this.firstLogin = firstLogin;
    }

}