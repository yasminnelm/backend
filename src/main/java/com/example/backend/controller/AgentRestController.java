package com.example.backend.controller;

import com.example.backend.model.dto.AgentDTO;
import com.example.backend.service.AgentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/api/agents")
@CrossOrigin("*")
public class AgentRestController {

    private final AgentService agentService;

    @Autowired
    public AgentRestController(AgentService agentService) {
        this.agentService = agentService;
    }

    @GetMapping
    public List<AgentDTO> getAllAgents() {
        return agentService.findAllAgents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgentDTO> getAgentById(@PathVariable Long id) {
        return agentService.getAgentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<?> createAgent(@RequestParam String lastname,
                                         @RequestParam String firstname,
                                         @RequestParam String email,
                                         @RequestParam String emailConfirmation,
                                         @RequestParam String phonenumber,
                                         @RequestParam("cinRecto") MultipartFile cinRecto,
                                         @RequestParam("cinVerso") MultipartFile cinVerso,
                                         @RequestParam String numCin,
                                         @RequestParam String address,
                                         @RequestParam String description,
                                         @RequestParam String birthdate,
                                         @RequestParam Long numLicence,
                                         @RequestParam Long numRegCom) {

        try {
            byte[] cinRectoBytes = cinRecto.getBytes();
            byte[] cinVersoBytes = cinVerso.getBytes();
            AgentDTO agentDTO = agentService.createAgent(lastname, firstname, email, emailConfirmation,
                    numCin, address, phonenumber, description, birthdate, numLicence, numRegCom,
                    cinRectoBytes, cinVersoBytes);
            return ResponseEntity.ok(agentDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body("Error saving Agent"+e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Internal server error: " + e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error reading CIN files: " + e.getMessage());
        }
    }






    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAgent(@PathVariable Long id) {
        try {
            agentService.deleteAgent(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
