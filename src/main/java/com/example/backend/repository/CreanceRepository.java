package com.example.backend.repository;

import com.example.backend.model.entity.Creance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CreanceRepository extends JpaRepository<Creance, Long> {
    Creance findCreancierById(Long id);
}

