package com.example.backend.controller;

import com.example.backend.model.dto.ClientDTO;
import com.example.backend.model.entity.Agent;
import com.example.backend.model.entity.Client;
import com.example.backend.repository.AgentRepository;
import com.example.backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pwd")
public class PasswordController {

    AgentRepository agentRepository;
    ClientRepository clientRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public PasswordController(AgentRepository agentRepository, @Lazy PasswordEncoder passwordEncoder,ClientRepository clientRepository) {
        this.agentRepository = agentRepository;
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/change-agent")
    public ResponseEntity<?> changeAgentPassword(
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

    @PostMapping("/change-client")
    public ResponseEntity<?> changeClientPassword(
            @RequestParam("email") String email,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmPassword") String confirmPassword) {

        Client client = clientRepository.findClientByEmail(email);
        if (client == null) {
            return ResponseEntity.status(404).body("Agent not found");
        }
        if(!newPassword.equals(confirmPassword)) {
            return ResponseEntity.status(400).body("Passwords do not match");
        }
        client.setPassword(newPassword);
        client.setFirstLogin(false);
        clientRepository.save(client);
        return ResponseEntity.ok().body("Password changed successfully");
    }

}
