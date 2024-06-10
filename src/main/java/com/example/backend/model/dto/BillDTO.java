package com.example.backend.model.dto;

import com.example.backend.model.entity.Biller;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDTO {
    private Long id;
    private String codeCreance;
    private String name;
    private double price;
    private Long billerId;
}