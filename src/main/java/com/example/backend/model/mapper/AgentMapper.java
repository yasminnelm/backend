package com.example.backend.model.mapper;

import com.example.backend.model.entity.Agent;
import com.example.backend.model.dto.AgentDTO;

public class AgentMapper {

    // Convertir de Agent à AgentDTO
    public static AgentDTO toDto(Agent agent) {
        if (agent == null) {
            return null;
        }

        return new AgentDTO(
                agent.getId(),
                agent.getUid(),
                agent.getNom(),
                agent.getPrenom(),
                agent.getEmail(),
                agent.getPassword(),
                agent.getNumCin(),
                agent.getAdresse(),
                agent.getTelephone(),
                agent.getDescription(),
                agent.getCinRectoPath(),
                agent.getCinVersoPath(),
                agent.getDateNaissance(),
                agent.getNumPatente(),
                agent.getNumRegCom(),
                agent.isFirstLogin()
        );
    }

    // Convertir de AgentDTO à Agent
    public static Agent toEntity(AgentDTO agentDTO) {
        if (agentDTO == null) {
            return null;
        }

        return new Agent(
                agentDTO.getId(),
                agentDTO.getUid(),
                agentDTO.getNom(),
                agentDTO.getPrenom(),
                agentDTO.getEmail(),
                agentDTO.getPassword(),
                agentDTO.getNumCin(),
                agentDTO.getAdresse(),
                agentDTO.getTelephone(),
                agentDTO.getDescription(),
                agentDTO.getCinRectoPath(),
                agentDTO.getCinVersoPath(),
                agentDTO.getDateNaissance(),
                agentDTO.getNumPatente(),
                agentDTO.getNumRegCom(),
                agentDTO.isFirstLogin()
        );
    }
}
