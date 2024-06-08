package com.example.backend.controller;

import com.example.backend.model.dto.ClientDTO;
import com.example.backend.service.CmiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CMIController {

    @PostMapping("/verify")
    public ResponseEntity<CmiResponse> verifyClient(@RequestBody ClientDTO clientDTO) {
        // Simulate the logic to determine if the response is favorable
        // For example purposes, let's say the response is always favorable if the email is not empty
        boolean isFavorable = clientDTO.getEmail() != null && !clientDTO.getEmail().isEmpty();

        CmiResponse response = new CmiResponse();
        response.setFavorable(isFavorable);

        return ResponseEntity.ok(response);
    }
}

