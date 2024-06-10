package com.example.backend.model.dto;

import com.example.backend.model.entity.Bill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillerDTO {
    private Long id;
    private String name;
    private String billCategory;
    private List<Bill> bills;
    private List<Long> invoiceIds;
}