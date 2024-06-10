package com.example.backend.controller;

import com.example.backend.model.dto.AccountOperationDTO;
import com.example.backend.model.dto.ClientDTO;
import com.example.backend.model.entity.AccountOperation;
import com.example.backend.model.entity.BankAccount;
import com.example.backend.model.entity.Client;
import com.example.backend.model.enumeration.AccountType;
import com.example.backend.service.BankAccountService;
import com.example.backend.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@PreAuthorize("hasAuthority('ROLE_AGENT')")
@RequestMapping("/api/clients")
@CrossOrigin("*")
public class ClientRestController {
    @Autowired
    BankAccountService bankAccountService;
    private final ClientService clientService;

    @Autowired
    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("")
    public List<ClientDTO> findAllClients() {
        return clientService.findAllClients();
    }

    @PostMapping("")
    @Transactional
    public ResponseEntity<?> createClient(@RequestParam String lastname,
                                          @RequestParam String firstname,
                                          @RequestParam String account_type,
                                          @RequestParam String email,
                                          @RequestParam String phonenumber,
                                          @RequestParam("cinRecto") MultipartFile cinRecto,
                                          @RequestParam("cinVerso") MultipartFile cinVerso) {
        try {
            byte[] cinRectoBytes = cinRecto.getBytes();
            byte[] cinVersoBytes = cinVerso.getBytes();

            phonenumber = "+" + phonenumber;
            if (!phonenumber.matches("^\\+212[6-7][0-9]{8}$")) {
                throw new IllegalArgumentException("Phone number does not match the Moroccan form");
            }

            ClientDTO clientDTO = new ClientDTO();
            clientDTO.setLastname(lastname);
            clientDTO.setFirstname(firstname);
            clientDTO.setEmail(email);
            clientDTO.setPhonenumber(phonenumber);
            clientDTO.setCinRectoPath(cinRectoBytes);
            clientDTO.setCinVersoPath(cinVersoBytes);
            clientDTO.setFirstLogin(true);

            Client savedClient = clientService.registerClient(clientDTO);
            //System.out.println(savedClient.getEmail());
            //System.out.println(AccountType.valueOf(account_type));

            BankAccount savedBankAccount = bankAccountService.createAccountWithClient(AccountType.valueOf(account_type),savedClient);
            clientService.updateClient(savedClient,savedBankAccount);
            System.out.println(savedBankAccount.getAccountNumber());


            Map<String, String> response = new HashMap<>();
            response.put("message", "Account & Client created successfully in JIBI");

            return ResponseEntity.ok(response);

        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error reading files: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Internal server error: " + e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        try {
            clientService.deleteClientById(id);
            return ResponseEntity.ok("Client deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Internal server error: " + e.getMessage());
        }
    }
}
