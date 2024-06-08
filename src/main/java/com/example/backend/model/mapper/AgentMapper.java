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
                agent.getLastname(),
                agent.getFirstname(),
                agent.getEmail(),
                agent.getPassword(),
                agent.getNumCin(),
                agent.getAddress(),
                agent.getPhonenumber(),
                agent.getDescription(),
                agent.getCinRectoPath(),
                agent.getCinVersoPath(),
                agent.getBirthdate(),
                agent.getNumLicence(),
                agent.getNumRegCom(),
                agent.isFirstLogin(),
                agent.getRole()
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
                agentDTO.getLastname(),
                agentDTO.getFirstname(),
                agentDTO.getEmail(),
                agentDTO.getPassword(),
                agentDTO.getNumCin(),
                agentDTO.getAddress(),
                agentDTO.getPhonenumber(),
                agentDTO.getDescription(),
                agentDTO.getCinRectoPath(),
                agentDTO.getCinVersoPath(),
                agentDTO.getBirthdate(),
                agentDTO.getNumLicence(),
                agentDTO.getNumRegCom(),
                agentDTO.isFirstLogin(),
                agentDTO.getRole()

        );
    }
}
