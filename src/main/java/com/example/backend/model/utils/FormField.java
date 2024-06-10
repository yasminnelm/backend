package com.example.backend.model.utils;

import com.example.backend.model.enumeration.FieldType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class FormField {
    private String name;
    private String label;
    private FieldType type;
    private boolean required;


}
