package com.example.backend.controller;

import com.example.backend.jwt.JwtProvider;
import com.example.backend.model.dto.AgentDTO;
import com.example.backend.model.dto.BankAccountDTO;
import com.example.backend.model.dto.ClientDTO;
import com.example.backend.model.entity.Client;
import com.example.backend.model.mapper.BankAccountMapper;
import com.example.backend.model.mapper.ClientMapper;
import com.example.backend.repository.BankAccountRepository;
import com.example.backend.repository.ClientRepository;
import com.example.backend.service.AgentService;
import com.example.backend.service.BankAccountService;
import com.example.backend.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
@CrossOrigin("*")
public class AuthController {

    private final AgentService agentService;
    private final ClientRepository clientRepository;
    private final JwtProvider jwtProvider;
    private final BankAccountService bankAccountService;
    @Autowired
    public AuthController(AgentService agentService, ClientRepository clientRepository, JwtProvider jwtProvider, BankAccountService bankAccountService) {
        this.agentService = agentService;
        this.clientRepository = clientRepository;
        this.jwtProvider = jwtProvider;
        this.bankAccountService = bankAccountService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        AgentDTO agentDTO = agentService.getAgentByEmail(email);
        Client client = clientRepository.findClientByEmail(email);
        Authentication authentication = null;
        Map<Object, Object> response = new HashMap<>();


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
                response.put("firstlogin", String.valueOf(agentDTO.isFirstLogin()));

            } else {
                authentication = new UsernamePasswordAuthenticationToken(
                        email,
                        password,
                        Collections.singletonList(
                                new SimpleGrantedAuthority("ROLE_AGENT")
                        )
                );
            }
        } else if (client != null) {
            if (!client.getPassword().equals(password)) {
                return ResponseEntity.status(401).body("Incorrect password");
            }
            authentication = new UsernamePasswordAuthenticationToken(
                    email,
                    password,
                    Collections.singletonList(
                            new SimpleGrantedAuthority("ROLE_CLIENT")
                    )
            );
            ClientDTO clientDTO = ClientMapper.INSTANCE.toDto(client);
            //System.out.println(clientDTO);
            clientDTO.setCinRectoPath(null);
            clientDTO.setCinVersoPath(null);
            clientDTO.setBankAccountDTO(bankAccountService.findBankAccountByClient(client));
            response.put("clientDTO", clientDTO);
            response.put("clientId", String.valueOf(client.getId()));
            if (client.isFirstLogin()) {
                response.put("firstlogin", String.valueOf(clientDTO.isFirstLogin()));
                //return ResponseEntity.status(302).body("First login, change your password");

            }

        } else {
            if (!email.equals("admin@gmail.com") || !password.equals("12345")) {
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
        // Créer un objet JSON pour renvoyer le token et le rôle
        response.put("token", token);
        response.put("role", authentication.getAuthorities().iterator().next().getAuthority());



        return ResponseEntity.ok(response);    }
}

