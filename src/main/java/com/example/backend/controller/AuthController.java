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
    private final ClientRepository clientRepository;
    private final JwtProvider jwtProvider;

    @Autowired
    public AuthController(AgentService agentService, ClientRepository clientRepository, JwtProvider jwtProvider) {
        this.agentService = agentService;
        this.clientRepository = clientRepository;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        AgentDTO agentDTO = agentService.getAgentByEmail(email);
        Client client = clientRepository.findClientByEmail(email);
        Authentication authentication = null;

        if (agentDTO != null) {
            if (!agentDTO.getPassword().equals(password)) {
                return ResponseEntity.status(401).body("Incorrect Agent password");
            }
            if (agentDTO.isFirstLogin()) {
                authentication = new UsernamePasswordAuthenticationToken(
                        email,
                        password,
                        Collections.singletonList(
                                new SimpleGrantedAuthority("ROLE_AGENT")
                        )
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String token = jwtProvider.generateToken(authentication);
                System.out.println(token);
                return ResponseEntity.status(302).body("First login, change your password");
            }
            authentication = new UsernamePasswordAuthenticationToken(
                    email,
                    password,
                    Collections.singletonList(
                            new SimpleGrantedAuthority("ROLE_AGENT")
                    )
            );
        } else if (client != null) {
            if (!client.getPassword().equals(password)) {
                return ResponseEntity.status(401).body("Incorrect password");
            }
            if (client.isFirstLogin()) {
                return ResponseEntity.status(302).body("First login, change your password");
            }
            authentication = new UsernamePasswordAuthenticationToken(
                    email,
                    password,
                    Collections.singletonList(
                            new SimpleGrantedAuthority("ROLE_CLIENT")
                    )
            );
        } else {
            if (!email.equals("admin") || !password.equals("12345")) {
                return ResponseEntity.status(401).body("Incorrect password");
            }
            authentication = new UsernamePasswordAuthenticationToken(
                    email,
                    password,
                    Collections.singletonList(
                            new SimpleGrantedAuthority("ROLE_ADMIN")
                    )
            );
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return ResponseEntity.ok(token);
    }
}
