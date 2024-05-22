package com.example.backend.controller;

import com.example.backend.model.Agent;
import com.example.backend.service.AgentService;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
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
    public List<Agent> getAllAgents() {
        return agentService.findAll();
    }



    @GetMapping("/{id}")
    public ResponseEntity<Agent> getAgentById(@PathVariable Long id) {
        return agentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
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
                                         @RequestParam String dateNaissance,//change to date
                                         @RequestParam Long numPatente,
                                         @RequestParam Long numRegCom) {

        try {
            Agent agent = agentService.createAgent( nom,  prenom,  email,  emailConfirmation,
                       numCin,  adresse,  telephone,  description,
                     cinRecto,  cinVerso,  dateNaissance, numPatente,  numRegCom);
            return ResponseEntity.ok(agent);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body("error saving Agent"+e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password){
        Agent agent = agentService.findAgent(email);
        if (agent == null) {
            return ResponseEntity.status(404).body("Agent not found");
        }
        if (!agent.getPassword().equals(password)) {
            return ResponseEntity.status(401).body("Incorrect password");
        }

        if (agent.isFirstLogin()) {
            return ResponseEntity.status(302).body("First login, change your password");
        }

        return ResponseEntity.ok(agent);
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
