package com.example.backend.controller;// FormController.java

import com.example.backend.model.utils.Form;
import com.example.backend.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/forms")
@PreAuthorize("hasAuthority('ROLE_CLIENT')")

public class FormController {

    @Autowired
    private FormService formService;

    @GetMapping("/{type}")
    public Form getFormByType(@PathVariable String type) {
        return formService.getFormByBillCategory(type);
    }
}
