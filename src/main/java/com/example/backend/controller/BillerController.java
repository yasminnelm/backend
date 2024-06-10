package com.example.backend.controller;

import com.example.backend.model.dto.BillerDTO;
import com.example.backend.model.entity.Biller;
import com.example.backend.repository.BillerRepository;
import com.example.backend.service.BillerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/listecreanciers")
public class BillerController {


    @Autowired
    private BillerService billerService;
    @Autowired
    private BillerRepository billerRepository;


    @GetMapping("")
    public List<Biller> getAllBillers() {
        return billerRepository.findAll();
    }

}