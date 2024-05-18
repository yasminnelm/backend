package com.example.backend.controller;

import com.example.backend.model.Agent;
import com.example.backend.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordController {
    @Autowired
    AgentRepository agentRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @PostMapping("/changePassword")
    public String changePassword(@RequestParam("newPassword") String newPassword) {
        Agent agent = (Agent) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        agent.setPassword(passwordEncoder.encode(newPassword));
        agent.setFirstLogin(false);
        agentRepo.save(agent);
        return "redirect:/";


    }

}
