package com.example.backend.controller;


import com.example.backend.jwt.JwtProvider;
import com.example.backend.model.entity.Agent;
import com.example.backend.model.entity.Client;
import com.example.backend.repository.AgentRepository;
import com.example.backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/password")
public class PasswordController {


    private final AgentRepository agentRepository;
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;


    @Autowired
    public PasswordController(AgentRepository agentRepository, ClientRepository clientRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.agentRepository = agentRepository;
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/agent")
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
        } else {
            agent.setPassword(newPassword);
            agent.setFirstLogin(false);
            agentRepository.save(agent);
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(
                            email,
                            newPassword,
                            Collections.singletonList(
                                    new SimpleGrantedAuthority("ROLE_AGENT")
                            )

            );
            SecurityContextHolder.getContext().setAuthentication(authentication);


            String token = jwtProvider.generateToken(authentication);

            return ResponseEntity.status(200).body(token);
        }


    }

    @PostMapping("/client")
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
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        email,
                        newPassword,
                        Collections.singletonList(
                                new SimpleGrantedAuthority("ROLE_CLIENT")
                        )

        );
        SecurityContextHolder.getContext().setAuthentication(authentication);


        String token = jwtProvider.generateToken(authentication);

        return ResponseEntity.ok(token);
    }

}
