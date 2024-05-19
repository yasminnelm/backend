package com.example.backend.controller;

import com.example.backend.model.Agent;
import com.example.backend.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/agents")
public class AgentRestController {

    private final AgentRepository agentRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AgentRestController(AgentRepository agentRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.agentRepository = agentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agent> getAgentById(@PathVariable Long id) {
        return agentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<?> createAgent(@RequestParam String nom,
                                         @RequestParam String prenom,
                                         @RequestParam String email,
                                         @RequestParam String telephone,
                                         @RequestParam("cinRecto") MultipartFile cinRecto,
                                         @RequestParam("cinVerso") MultipartFile cinVerso) {

        String uid = UUID.randomUUID().toString();
        String password = passwordEncoder.encode("temporaryPassword");

        Agent agent = new Agent();
        agent.setUid(uid);
        agent.setPassword(password);
        agent.setNom(nom);
        agent.setPrenom(prenom);
        agent.setEmail(email);
        agent.setTelephone(telephone);
        agent.setFirstLogin(true);

        try {
            byte[] cinRectoPath =cinRecto.getBytes();
            byte[] cinVersoPath =cinVerso.getBytes();
            agent.setCinRectoPath(cinRectoPath);
            agent.setCinVersoPath(cinVersoPath);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error saving files");
        }

        agentRepository.save(agent);
        return ResponseEntity.ok(agent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agent> updateAgent(@PathVariable Long id, @RequestBody Agent updatedAgent) {
        return agentRepository.findById(id)
                .map(agent -> {
                    agent.setNom(updatedAgent.getNom());
                    agent.setPrenom(updatedAgent.getPrenom());
                    agent.setEmail(updatedAgent.getEmail());
                    agent.setTelephone(updatedAgent.getTelephone());
                    agentRepository.save(agent);
                    return ResponseEntity.ok(agent);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAgent(@PathVariable Long id) {
        if (agentRepository.existsById(id)) {
            agentRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private String saveFile(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        File dest = new File("uploads/" + fileName);
        file.transferTo(dest);
        return dest.getAbsolutePath();
    }
}
