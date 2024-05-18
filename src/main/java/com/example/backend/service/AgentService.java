package com.example.backend.service;

import com.example.backend.model.Agent;
import com.example.backend.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgentService {
    private final AgentRepository agentRepo;

    @Autowired
    public AgentService(AgentRepository agentRepo) {
        this.agentRepo = agentRepo;
    }
 public Agent createAgent(Agent agent) {
       return agentRepo.save(agent);

 }
 public List<Agent> findAllAgents()
     {
         return agentRepo.findAll();
     }
 public Optional<Agent> getAgentById(Long id)
 {
     return agentRepo.findById(id);
 }


}
