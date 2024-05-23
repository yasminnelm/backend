package com.example.backend.controller;

import com.example.backend.model.dto.AgentDTO;
import com.example.backend.service.AgentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/agents")
public class AgentRestController {

    private final AgentService agentService;

    @Autowired
    public AgentRestController(AgentService agentService) {
        this.agentService = agentService;
    }

    @GetMapping
    public List<AgentDTO> getAllAgents() {
        return agentService.findAllAgents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgentDTO> getAgentById(@PathVariable Long id) {
        return agentService.getAgentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<?> createAgent(@RequestParam String nom,
                                         @RequestParam String prenom,
                                         @RequestParam String email,
                                         @RequestParam String emailConfirmation,
                                         @RequestParam String telephone,
                                         @RequestParam("cinRecto") MultipartFile cinRecto,
                                         @RequestParam("cinVerso") MultipartFile cinVerso,
                                         @RequestParam String numCin,
                                         @RequestParam String adresse,
                                         @RequestParam String description,
                                         @RequestParam String dateNaissance,
                                         @RequestParam Long numPatente,
                                         @RequestParam Long numRegCom) {

        try {
            byte[] cinRectoBytes = cinRecto.getBytes();
            byte[] cinVersoBytes = cinVerso.getBytes();
            AgentDTO agentDTO = agentService.createAgent(nom, prenom, email, emailConfirmation,
                    numCin, adresse, telephone, description, dateNaissance, numPatente, numRegCom,
                    cinRectoBytes, cinVersoBytes);
            return ResponseEntity.ok(agentDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body("Error saving Agent"+e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Internal server error: " + e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error reading CIN files: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password){
        AgentDTO agentDTO = agentService.getAgentByEmail(email);
        if (agentDTO == null) {
            return ResponseEntity.status(404).body("Agent not found");
        }
        if (!agentDTO.getPassword().equals(password)) {
            return ResponseEntity.status(401).body("Incorrect password");
        }

        if (agentDTO.isFirstLogin()) {
            return ResponseEntity.status(302).body("First login, change your password");
        }

        return ResponseEntity.ok(agentDTO);
    }



//    @PutMapping("/{id}")
//    public ResponseEntity<Agent> updateAgent(@PathVariable Long id, @RequestBody Agent updatedAgent) {
//        try {
//            Agent agent = agentService.updateAgent(id, updatedAgent);
//            return ResponseEntity.ok(agent);
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(400).body("no");
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(500).body("ok");
//        }
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAgent(@PathVariable Long id) {
        try {
            agentService.deleteAgent(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
