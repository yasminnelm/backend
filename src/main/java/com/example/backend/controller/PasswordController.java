package com.example.backend.controller;

import com.example.backend.model.Agent;
import com.example.backend.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pwd")
public class PasswordController {

    AgentRepository agentRepository;
    PasswordEncoder passwordEncoder;
    @Autowired
    public PasswordController(AgentRepository agentRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.agentRepository = agentRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping("/change")
    public ResponseEntity<?> changePassword(
            @RequestParam("email") String email,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmPassword") String confirmPassword) {

        Agent agent = agentRepository.findAgentByEmail(email);
        if (agent == null) {
            return ResponseEntity.status(404).body("Agent not found");
        }
        if(!newPassword.equals(confirmPassword)) {
            return ResponseEntity.status(400).body("Passwords do not match");
        }
        agent.setPassword(newPassword);
        agent.setFirstLogin(false);
        agentRepository.save(agent);
        return ResponseEntity.ok().body("Password changed successfully");
    }

}
