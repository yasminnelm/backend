package com.example.backend.model.mapper;

import com.example.backend.model.dto.AgentDTO;
import com.example.backend.model.entity.Agent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AgentMapper {

    AgentMapper INSTANCE = Mappers.getMapper(AgentMapper.class);

    AgentDTO toDto(Agent agent);

    Agent toEntity(AgentDTO agentDTO);
}