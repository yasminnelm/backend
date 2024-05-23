package com.example.backend.service;

import com.example.backend.model.dto.AgentDTO;
import com.example.backend.model.entity.Agent;
import com.example.backend.model.mapper.AgentMapper;
import com.example.backend.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AgentService {
    private final AgentRepository agentRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailPasswordService mailPasswordService;


    @Autowired
    public AgentService(AgentRepository agentRepo, PasswordEncoder passwordEncoder, MailPasswordService mailPasswordService) {
        this.agentRepository = agentRepo;
        this.passwordEncoder = passwordEncoder;
        this.mailPasswordService = mailPasswordService;
    }

    public AgentDTO createAgent(String nom, String prenom, String email, String emailConfirmation,
                                String numCin, String adresse, String telephone, String description,
                                String dateNaissance, Long numPatente, Long numRegCom,
                                byte[] cinRectoPath, byte[] cinVersoPath) {
        //confirmation de l'email
        if (!email.equals(emailConfirmation)) {
            throw new IllegalArgumentException("Email does not match confirmation");
        }
        //confirmation du numero de telephone
        telephone = "+" + telephone;
        if (!telephone.matches("^\\+212[6-7][0-9]{8}$")) {
            throw new IllegalArgumentException("Phone number does not match the moroccan form");
        }

        String uid = UUID.randomUUID().toString();
        String password=mailPasswordService.generateDefaultPassword();

        Agent agent = new Agent();
        agent.setUid(uid);

        agent.setPassword(passwordEncoder.encode(password));
        agent.setNom(nom);
        agent.setPrenom(prenom);
        agent.setEmail(email);
        agent.setNumCin(numCin);
        agent.setAdresse(adresse);
        agent.setTelephone(telephone);
        agent.setDescription(description);
        agent.setDateNaissance(dateNaissance);
        agent.setNumPatente(numPatente);
        agent.setNumRegCom(numRegCom);

        try {
            agent.setCinRectoPath(cinRectoPath);
            agent.setCinVersoPath(cinVersoPath);
        } catch (Exception e) {
            throw new RuntimeException("Error saving CIN files", e);
        }
        Agent savedAgent = agentRepository.save(agent);
        mailPasswordService.sendPasswordByEmail(email, password);

        return AgentMapper.toDto(savedAgent);

    }


     public List<AgentDTO> findAllAgents()
         {
             return agentRepository.findAll().stream()
                     .map(AgentMapper::toDto)
                     .collect(Collectors.toList());
         }


     public Optional<AgentDTO> getAgentById(Long id)
     {
         return agentRepository.findById(id)
                 .map(AgentMapper::toDto);
     }

    public AgentDTO getAgentByEmail(String email)
    {
        return AgentMapper.toDto(
                agentRepository.findAgentByEmail(email)
        );
    }

    public AgentDTO updateAgent(Long id, AgentDTO updatedAgentDTO) {
        Optional<Agent> optionalAgent = agentRepository.findById(id);
        if (optionalAgent.isPresent()) {
            Agent agent = optionalAgent.get();
            agent.setNom(updatedAgentDTO.getNom());
            agent.setPrenom(updatedAgentDTO.getPrenom());
            agent.setEmail(updatedAgentDTO.getEmail());
            agent.setNumCin(updatedAgentDTO.getNumCin());
            agent.setAdresse(updatedAgentDTO.getAdresse());
            agent.setTelephone(updatedAgentDTO.getTelephone());
            agent.setDescription(updatedAgentDTO.getDescription());
            agent.setDateNaissance(updatedAgentDTO.getDateNaissance());
            agent.setNumPatente(updatedAgentDTO.getNumPatente());
            agent.setNumRegCom(updatedAgentDTO.getNumRegCom());
            agent.setFirstLogin(updatedAgentDTO.isFirstLogin());
            // Note: Password is not updated here for security reasons.

            Agent updatedAgent = agentRepository.save(agent);
            return AgentMapper.toDto(updatedAgent);
        } else {
            throw new IllegalArgumentException("Agent not found");
        }
    }

    public void deleteAgent(Long id) {
        agentRepository.deleteById(id);
    }


}
