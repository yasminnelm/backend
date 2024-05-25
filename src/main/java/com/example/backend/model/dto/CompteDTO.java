package com.example.backend.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompteDTO {
    private Long id;
    private double plafond;
    private String type;
    private Long clientId;
}
