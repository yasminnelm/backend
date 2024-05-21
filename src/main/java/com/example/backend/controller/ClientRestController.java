package com.example.backend.controller;

import com.example.backend.model.dto.AgentDTO;
import com.example.backend.model.dto.ClientDTO;
import com.example.backend.model.enumeration.CompteType;
import com.example.backend.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.management.openmbean.CompositeType;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientRestController {


    private final ClientService clientService;

    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }
    @GetMapping("")
    public List<ClientDTO> findAllClients() {
        return clientService.findAllClients();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/add")
    public ResponseEntity<?> createClient(@RequestParam String nom,
                                          @RequestParam String prenom,
                                          @RequestParam String email,
                                          @RequestParam String telephone,
                                          @RequestParam("cinRecto") MultipartFile cinRecto,
                                          @RequestParam("cinVerso") MultipartFile cinVerso,
                                          @RequestParam String compteType,
                                          @RequestParam double plafond
                                          ) {

        try {
            byte[] cinRectoBytes = cinRecto.getBytes();
            byte[] cinVersoBytes = cinVerso.getBytes();

            ClientDTO clientDTO = clientService.createClient(nom, prenom,email,telephone,
                    cinRectoBytes, cinVersoBytes,compteType,plafond);

            return ResponseEntity.ok(clientDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body("Error saving client: " + e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error reading files: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Internal server error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id) {
        try{
            clientService.deleteClient(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }

    }
}


