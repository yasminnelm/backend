package com.example.backend.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompteDTO {
    private Long id;
    private double balance;
    private String type;
    private Long clientId;
}
