package com.example.backend.service;

import com.example.backend.model.dto.AgentDTO;
import com.example.backend.model.entity.Agent;
import com.example.backend.model.mapper.AgentMapper;
import com.example.backend.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AgentService {
    private final AgentRepository agentRepository;
//    private final PasswordEncoder passwordEncoder;
    private final MailPasswordService mailPasswordService;


    @Autowired
    public AgentService(AgentRepository agentRepo, MailPasswordService mailPasswordService) {
        this.agentRepository = agentRepo;
//        this.passwordEncoder = passwordEncoder;
        this.mailPasswordService = mailPasswordService;
    }

    public AgentDTO createAgent(String lastname, String firstname, String email, String emailConfirmation,
                                String numCin, String address, String phonenumber, String description,
                                String birthdate, Long numLicence, Long numRegCom,
                                byte[] cinRectoPath, byte[] cinVersoPath) {
        //confirmation de l'email
        if (!email.equals(emailConfirmation)) {
            throw new IllegalArgumentException("Email does not match confirmation");
        }
        //confirmation du numero de phonenumber
        phonenumber = "+" + phonenumber;
        if (!phonenumber.matches("^\\+212[6-7][0-9]{8}$")) {
            throw new IllegalArgumentException("Phone number does not match the moroccan form");
        }

        String uid = UUID.randomUUID().toString();
        String password=mailPasswordService.generateDefaultPassword();

        Agent agent = new Agent();
        agent.setUid(uid);

        agent.setPassword(password);
        agent.setLastname(lastname);
        agent.setFirstname(firstname);
        agent.setEmail(email);
        agent.setNumCin(numCin);
        agent.setAddress(address);
        agent.setPhonenumber(phonenumber);
        agent.setDescription(description);
        agent.setBirthdate(birthdate);
        agent.setNumLicence(numLicence);
        agent.setNumRegCom(numRegCom);

        try {
            agent.setCinRectoPath(cinRectoPath);
            agent.setCinVersoPath(cinVersoPath);
        } catch (Exception e) {
            throw new RuntimeException("Error saving CIN files", e);
        }
        Agent savedAgent = agentRepository.save(agent);
        mailPasswordService.sendPasswordByEmail(email, password);

        return AgentMapper.INSTANCE.toDto(savedAgent);

    }


     public List<AgentDTO> findAllAgents()
         {
             return agentRepository.findAll().stream()
                     .map(AgentMapper.INSTANCE::toDto)
                     .collect(Collectors.toList());
         }


     public Optional<AgentDTO> getAgentById(Long id)
     {
         return agentRepository.findById(id)
                 .map(AgentMapper.INSTANCE::toDto);
     }

    public AgentDTO getAgentByEmail(String email)
    {
        return AgentMapper.INSTANCE.toDto(
                agentRepository.findAgentByEmail(email)
        );
    }

    public AgentDTO updateAgent(Long id, AgentDTO updatedAgentDTO) {
        Optional<Agent> optionalAgent = agentRepository.findById(id);
        if (optionalAgent.isPresent()) {
            Agent agent = optionalAgent.get();
            agent.setLastname(updatedAgentDTO.getLastname());
            agent.setFirstname(updatedAgentDTO.getFirstname());
            agent.setEmail(updatedAgentDTO.getEmail());
            agent.setNumCin(updatedAgentDTO.getNumCin());
            agent.setAddress(updatedAgentDTO.getAddress());
            agent.setPhonenumber(updatedAgentDTO.getPhonenumber());
            agent.setDescription(updatedAgentDTO.getDescription());
            agent.setBirthdate(updatedAgentDTO.getBirthdate());
            agent.setNumLicence(updatedAgentDTO.getNumLicence());
            agent.setNumRegCom(updatedAgentDTO.getNumRegCom());
            agent.setFirstLogin(updatedAgentDTO.isFirstLogin());
            // Note: Password is not updated here for security reasons.

            Agent updatedAgent = agentRepository.save(agent);
            return AgentMapper.INSTANCE.toDto(updatedAgent);
        } else {
            throw new IllegalArgumentException("Agent not found");
        }
    }

    public void deleteAgent(Long id) {
        agentRepository.deleteById(id);
    }


}
