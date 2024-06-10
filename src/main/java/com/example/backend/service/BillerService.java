package com.example.backend.service;

import com.example.backend.model.dto.BillerDTO;
import com.example.backend.model.mapper.BillerMapper;
import com.example.backend.repository.BillerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillerService {

    @Autowired
    private BillerRepository billerRepository;


    public List<BillerDTO> listeCreances() {
        List<BillerDTO> creditors = billerRepository.findAllWithBills().stream()
                .map(BillerMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
        System.out.println("Creditors list: " + creditors);
        return creditors;
    }
}
