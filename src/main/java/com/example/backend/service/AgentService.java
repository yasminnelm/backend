package com.example.backend.service;

import com.example.backend.model.Agent;
import com.example.backend.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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


    public Agent createAgent(String nom, String prenom, String email, String emailConfirmation,
                              String numCin, String adresse, String telephone, String description,
                             MultipartFile cinRecto, MultipartFile cinVerso, String  dateNaissance,
                             Long numPatente, Long numRegCom) {
        //confirmation de l'email
        if (!email.equals(emailConfirmation)) {
            throw new IllegalArgumentException("Email does not match confirmation");
        }
        //confirmation du numero de telephone
        if (!telephone.matches("^\\+212[6-7][0-9]{8}$")) {
            throw new IllegalArgumentException("phone number does not match the moroccan form");
        }

        String uid = UUID.randomUUID().toString();
        String password=mailPasswordService.generateDefaultPassword();
        Agent agent = new Agent();
        agent.setUid(uid);

        agent.setPassword(password);
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
//        agent.setFirstLogin(true);

        try {
            byte[] cinRectoPath = cinRecto.getBytes();
            byte[] cinVersoPath = cinVerso.getBytes();
            agent.setCinRectoPath(cinRectoPath);
            agent.setCinVersoPath(cinVersoPath);
        } catch (IOException e) {
            throw new RuntimeException("Error saving files", e);
        }
        Agent savedAgent = agentRepository.save(agent);
        mailPasswordService.sendPasswordByEmail(email, password);

        return savedAgent;

    }
     public List<Agent> findAllAgents()
         {
             return agentRepository.findAll();
         }
     public Optional<Agent> getAgentById(Long id)
     {
         return agentRepository.findById(id);
     }

     public List<Agent> findAll() {
        return  agentRepository.findAll();
    }

    public Optional<Agent> findById(Long id) {
        return agentRepository.findById(id);
    }
//*************************************************************************************
    public Agent updateAgent(Long id, Agent updatedAgent) {
        return updatedAgent;
    }

    public void deleteAgent(Long id) {
        agentRepository.deleteById(id);
    }
}
