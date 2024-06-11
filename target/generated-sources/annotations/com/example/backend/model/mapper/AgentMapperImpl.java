package com.example.backend.model.mapper;

import com.example.backend.model.dto.AgentDTO;
import com.example.backend.model.entity.Agent;
import java.util.Arrays;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-11T13:29:07+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
public class AgentMapperImpl implements AgentMapper {

    @Override
    public AgentDTO toDto(Agent agent) {
        if ( agent == null ) {
            return null;
        }

        AgentDTO agentDTO = new AgentDTO();

        agentDTO.setId( agent.getId() );
        agentDTO.setUid( agent.getUid() );
        agentDTO.setLastname( agent.getLastname() );
        agentDTO.setFirstname( agent.getFirstname() );
        agentDTO.setEmail( agent.getEmail() );
        agentDTO.setPassword( agent.getPassword() );
        agentDTO.setNumCin( agent.getNumCin() );
        agentDTO.setAddress( agent.getAddress() );
        agentDTO.setPhonenumber( agent.getPhonenumber() );
        agentDTO.setDescription( agent.getDescription() );
        byte[] cinRectoPath = agent.getCinRectoPath();
        if ( cinRectoPath != null ) {
            agentDTO.setCinRectoPath( Arrays.copyOf( cinRectoPath, cinRectoPath.length ) );
        }
        byte[] cinVersoPath = agent.getCinVersoPath();
        if ( cinVersoPath != null ) {
            agentDTO.setCinVersoPath( Arrays.copyOf( cinVersoPath, cinVersoPath.length ) );
        }
        agentDTO.setBirthdate( agent.getBirthdate() );
        agentDTO.setNumLicence( agent.getNumLicence() );
        agentDTO.setNumRegCom( agent.getNumRegCom() );
        agentDTO.setFirstLogin( agent.isFirstLogin() );
        agentDTO.setRole( agent.getRole() );

        return agentDTO;
    }

    @Override
    public Agent toEntity(AgentDTO agentDTO) {
        if ( agentDTO == null ) {
            return null;
        }

        Agent agent = new Agent();

        agent.setId( agentDTO.getId() );
        agent.setUid( agentDTO.getUid() );
        agent.setLastname( agentDTO.getLastname() );
        agent.setFirstname( agentDTO.getFirstname() );
        agent.setEmail( agentDTO.getEmail() );
        agent.setPassword( agentDTO.getPassword() );
        agent.setNumCin( agentDTO.getNumCin() );
        agent.setAddress( agentDTO.getAddress() );
        agent.setPhonenumber( agentDTO.getPhonenumber() );
        agent.setDescription( agentDTO.getDescription() );
        byte[] cinRectoPath = agentDTO.getCinRectoPath();
        if ( cinRectoPath != null ) {
            agent.setCinRectoPath( Arrays.copyOf( cinRectoPath, cinRectoPath.length ) );
        }
        byte[] cinVersoPath = agentDTO.getCinVersoPath();
        if ( cinVersoPath != null ) {
            agent.setCinVersoPath( Arrays.copyOf( cinVersoPath, cinVersoPath.length ) );
        }
        agent.setBirthdate( agentDTO.getBirthdate() );
        agent.setNumLicence( agentDTO.getNumLicence() );
        agent.setNumRegCom( agentDTO.getNumRegCom() );
        agent.setFirstLogin( agentDTO.isFirstLogin() );
        agent.setRole( agentDTO.getRole() );

        return agent;
    }
}
