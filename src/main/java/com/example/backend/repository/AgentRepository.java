package com.example.backend.repository;

import com.example.backend.model.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent,Long> {
public Agent findAgentByUid(String uid);
public Agent findAgentByEmail(String email);
}
