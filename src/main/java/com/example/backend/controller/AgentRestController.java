package com.example.backend.controller;

import com.example.backend.model.Agent;
import com.example.backend.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/agent")
public class AgentRestController {
    private final AgentRepository agentRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AgentRestController(AgentRepository agentRepo, PasswordEncoder passwordEncoder) {
        this.agentRepo = agentRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/add")
    public Agent addAgent(@RequestBody Agent agent) {
        // Encode the agent's password before saving
        agent.setPassword(passwordEncoder.encode(agent.getPassword()));
        return agentRepo.save(agent);
    }

    @GetMapping("/list")
    public List<Agent> getAllAgents() {
        return agentRepo.findAll();
    }

    @GetMapping("/{id}")
    public Agent getAgentById(@PathVariable Long id) {
        return agentRepo.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteAgent(@PathVariable Long id) {
        agentRepo.deleteById(id);
    }

    private String saveFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        File dest = new File("uploads/" + fileName);
        file.transferTo(dest);
        return dest.getAbsolutePath();
    }
}
