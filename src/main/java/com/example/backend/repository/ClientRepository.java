package com.example.backend.repository;

import com.example.backend.model.dto.ClientDTO;
import com.example.backend.model.entity.Agent;
import com.example.backend.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    public Client findClientByEmail(String email);
    boolean existsByEmail(String email);

}
