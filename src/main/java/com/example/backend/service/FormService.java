package com.example.backend.service;// FormService.java

import com.example.backend.model.enumeration.FieldType;
import com.example.backend.model.utils.Form;
import com.example.backend.model.utils.FormField;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FormService {

    public Form getFormByBillCategory(String type) {
        Form form = new Form();
        form.setType(type);
        List<FormField> fields = new ArrayList<>();
        
        switch (type.toUpperCase()) {
            case "FACTURE":
                fields.add(new FormField("invoiceNumber", "Invoice Number", FieldType.TEXT, true));
                fields.add(new FormField("amount", "Amount", FieldType.NUMBER, true));
                fields.add(new FormField("dueDate", "Due Date", FieldType.DATE, true));
                break;
            case "DONATION":
                fields.add(new FormField("donorName", "Donor Name", FieldType.TEXT, true));
                fields.add(new FormField("amount", "Amount", FieldType.NUMBER, true));
                fields.add(new FormField("date", "Date", FieldType.DATE, true));
                break;
            case "RECHARGE":
                fields.add(new FormField("phoneNumber", "Phone Number", FieldType.TEXT, true));
                fields.add(new FormField("amount", "Amount", FieldType.NUMBER, true));
                break;
            case "VIREMENT":
                fields.add(new FormField("beneficiaryName", "Beneficiary Name", FieldType.TEXT, true));
                fields.add(new FormField("accountNumber", "Account Number", FieldType.NUMBER, true));
                fields.add(new FormField("amount", "Amount", FieldType.NUMBER, true));
                fields.add(new FormField("motif", "motif", FieldType.TEXT, true));
                fields.add(new FormField("date", "Date", FieldType.DATE, true));
                break;
            default:
                throw new IllegalArgumentException("Unknown bill category: " + type);
        }
        
        form.setFields(fields);
        
        return form;
    }
}
