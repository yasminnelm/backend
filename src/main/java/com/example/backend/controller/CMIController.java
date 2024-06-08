package com.example.backend.controller;

import com.example.backend.model.dto.ClientDTO;
import com.example.backend.model.entity.Client;
import com.example.backend.repository.ClientRepository;
import com.example.backend.service.CmiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CMIController {
    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/verify")
    public ResponseEntity<CmiResponse> verifyClient(@RequestBody ClientDTO clientDTO) {
        Optional<Client> clientExists = Optional.ofNullable(clientRepository.findClientByEmail(clientDTO.getEmail()));
        boolean isFavorable = !clientExists.isPresent();
        CmiResponse response = new CmiResponse();
        response.setFavorable(isFavorable);
        return ResponseEntity.ok(response);
    }
}