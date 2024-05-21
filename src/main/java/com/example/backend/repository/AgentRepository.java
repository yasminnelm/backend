package com.example.backend.repository;

import com.example.backend.model.dto.AgentDTO;
import com.example.backend.model.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent,Long> {
public AgentDTO findAgentByUid(String uid);
}
