package com.example.backend.controller;

import com.example.backend.model.entity.Agent;
import com.example.backend.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordController {

    AgentRepository agentRepository;
    PasswordEncoder passwordEncoder;
    @Autowired
    public PasswordController(AgentRepository agentRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.agentRepository = agentRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping("/changePassword")
    public String changePassword(@RequestParam("newPassword") String newPassword) {
        Agent agent = (Agent) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        agent.setPassword(passwordEncoder.encode(newPassword));
        agent.setFirstLogin(false);
        agentRepository.save(agent);
        return "redirect:/";


    }

}
