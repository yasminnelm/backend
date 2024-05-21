package com.example.backend.repository;

import com.example.backend.model.dto.ClientDTO;
import com.example.backend.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {

}
