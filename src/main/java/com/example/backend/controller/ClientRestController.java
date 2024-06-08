////package com.example.backend.controller;
////
////import com.example.backend.model.dto.AgentDTO;
////import com.example.backend.model.dto.ClientDTO;
////import com.example.backend.model.enumeration.CompteType;
////import com.example.backend.service.ClientService;
////import org.springframework.http.ResponseEntity;
////import org.springframework.web.bind.annotation.*;
////import org.springframework.web.multipart.MultipartFile;
////
////import javax.management.openmbean.CompositeType;
////import java.io.IOException;
////import java.util.List;
////
////@RestController
////@RequestMapping("/api/clients")
////public class ClientRestController {
////
////
////    private final ClientService clientService;
////
////    public ClientRestController(ClientService clientService) {
////        this.clientService = clientService;
////    }
////    @GetMapping("")
////    public List<ClientDTO> findAllClients() {
////        return clientService.findAllClients();
////    }
////    @GetMapping("/{id}")
////    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
////        return clientService.getClientById(id)
////                .map(ResponseEntity::ok)
////                .orElse(ResponseEntity.notFound().build());
////    }
////    @PostMapping("")
////    public ResponseEntity<?> createClient(@RequestParam String nom,
////                                          @RequestParam String prenom,
////                                          @RequestParam String email,
////                                          @RequestParam String telephone,
////                                          @RequestParam("cinRecto") MultipartFile cinRecto,
////                                          @RequestParam("cinVerso") MultipartFile cinVerso
////    ) {
////
////        try {
////            byte[] cinRectoBytes = cinRecto.getBytes();
////            byte[] cinVersoBytes = cinVerso.getBytes();
////
////            ClientDTO clientDTO = clientService.createClient(nom, prenom,email,telephone,
////                    cinRectoBytes, cinVersoBytes);
////
////            return ResponseEntity.ok(clientDTO);
////        } catch (IllegalArgumentException e) {
////            return ResponseEntity.status(400).body("Error saving client: " + e.getMessage());
////        } catch (IOException e) {
////            return ResponseEntity.status(500).body("Error reading files: " + e.getMessage());
////        } catch (RuntimeException e) {
////            return ResponseEntity.status(500).body("Internal server error: " + e.getMessage());
////        }
////    }
////
////    @DeleteMapping("/{id}")
////    public ResponseEntity<String> deleteClient(@PathVariable Long id) {
////        try{
////            clientService.deleteClient(id);
////            return ResponseEntity.ok().build();
////        } catch (IllegalArgumentException e) {
////            return ResponseEntity.status(400).body(e.getMessage());
////        } catch (RuntimeException e) {
////            return ResponseEntity.status(500).body(e.getMessage());
////        }
////
////    }
////}
//
//package com.example.backend.controller;
//
//import com.example.backend.model.dto.ClientDTO;
//import com.example.backend.model.dto.CMIResponseDTO;
//import com.example.backend.model.mapper.ClientMapper;
//import com.example.backend.service.CMIService;
//import com.example.backend.service.ClientService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
//@RestController
//@PreAuthorize("hasAuthority('ROLE_AGENT')")
//@RequestMapping("/api/clients")
//public class ClientRestController {
//
//    private final ClientService clientService;
//
//
//    @Autowired
//    public ClientRestController(ClientService clientService) {
//        this.clientService = clientService;
//    }
//
//    @PostMapping("")
//    public ResponseEntity<?> createClient(@RequestParam String nom,
//                                          @RequestParam String prenom,
//                                          @RequestParam String email,
//                                          @RequestParam String telephone,
//                                          @RequestParam("cinRecto") MultipartFile cinRecto,
//                                          @RequestParam("cinVerso") MultipartFile cinVerso
//                                          ) {
//        try {
//            byte[] cinRectoBytes = cinRecto.getBytes();
//            byte[] cinVersoBytes = cinVerso.getBytes();
//
//            telephone = "+" + telephone;
//            // Confirmation du numéro de téléphone
//            if (!telephone.matches("^\\+212[6-7][0-9]{8}$")) {
//                throw new IllegalArgumentException("Phone number does not match the Moroccan form");
//            }
//
//            ClientDTO clientDTO = new ClientDTO();
//            clientDTO.setNom(nom);
//            clientDTO.setPrenom(prenom);
//            clientDTO.setEmail(email);
//            clientDTO.setTelephone(telephone);
//            clientDTO.setCinRectoPath(cinRectoBytes);
//            clientDTO.setCinVersoPath(cinVersoBytes);
//
//            // Extract token from Authorization header
////            String token = authorizationHeader.replace("Bearer ", "");
////
////            // Call CMI service to create account
////            boolean accountCreated = cmiService.createAccountWithCMI(clientDTO, token);
//
//                // If account creation with CMI is successful, proceed with creating client in your platform
//                clientService.registerClient(clientDTO);
//                return ResponseEntity.ok("Account created successfully in JIBI");
//
//
//        } catch (IOException e) {
//            return ResponseEntity.status(500).body("Error reading files: " + e.getMessage());
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(500).body("Internal server error: " + e.getMessage());
//        }
//    }
//
//}

package com.example.backend.controller;

import com.example.backend.model.dto.ClientDTO;
import com.example.backend.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@PreAuthorize("hasAuthority('ROLE_AGENT')")
@RequestMapping("/api/clients")
public class ClientRestController {

    private final ClientService clientService;

    @Autowired
    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("")
    public ResponseEntity<?> createClient(@RequestParam String nom,
                                          @RequestParam String prenom,
                                          @RequestParam String email,
                                          @RequestParam String telephone,
                                          @RequestParam("cinRecto") MultipartFile cinRecto,
                                          @RequestParam("cinVerso") MultipartFile cinVerso) {
        try {
            byte[] cinRectoBytes = cinRecto.getBytes();
            byte[] cinVersoBytes = cinVerso.getBytes();

            telephone = "+" + telephone;
            if (!telephone.matches("^\\+212[6-7][0-9]{8}$")) {
                throw new IllegalArgumentException("Phone number does not match the Moroccan form");
            }

            ClientDTO clientDTO = new ClientDTO();
            clientDTO.setNom(nom);
            clientDTO.setPrenom(prenom);
            clientDTO.setEmail(email);
            clientDTO.setTelephone(telephone);
            clientDTO.setCinRectoPath(cinRectoBytes);
            clientDTO.setCinVersoPath(cinVersoBytes);

            clientService.registerClient(clientDTO);
            return ResponseEntity.ok("Account created successfully in JIBI");

        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error reading files: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Internal server error: " + e.getMessage());
        }
    }
}
