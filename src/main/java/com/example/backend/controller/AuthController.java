package com.example.backend.controller;

import com.example.backend.jwt.JwtProvider;
import com.example.backend.model.dto.AgentDTO;
import com.example.backend.model.entity.Client;
import com.example.backend.repository.ClientRepository;
import com.example.backend.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/login")
public class AuthController {


    private final AgentService agentService;
    private final JwtProvider jwtProvider;
    private final ClientRepository clientRepository;

    @Autowired
    public AuthController(AgentService agentService, JwtProvider jwtProvider, ClientRepository clientRepository) {
        this.agentService = agentService;
        this.jwtProvider = jwtProvider;
        this.clientRepository = clientRepository;
    }

    @PostMapping("/agent")
    public ResponseEntity<?> loginAgent(@RequestParam String email, @RequestParam String password) {
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


        Authentication authentication = new UsernamePasswordAuthenticationToken(
                email,
                password,
                Collections.singletonList(
                        new SimpleGrantedAuthority("ROLE_AGENT")
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);
        return ResponseEntity.ok(token);
    }



    //khdmtha b repo cus i cant figure out client mapper, so i cant use the clientdto here
    @PostMapping("/client")
    public ResponseEntity<?> loginClient(@RequestParam String email, @RequestParam String password) {
        Client client = clientRepository.findClientByEmail(email);
        if (client == null) {
            return ResponseEntity.status(404).body("Client not found");
        }
        if (!client.getPassword().equals(password)) {
            return ResponseEntity.status(401).body("Incorrect password");
        }
        if (client.isFirstLogin()) {
            return ResponseEntity.status(302).body("First login, change your password");
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                email,
                password,
                Collections.singletonList(
                        new SimpleGrantedAuthority("ROLE_CLIENT")
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        return ResponseEntity.ok(token);
    }
}
