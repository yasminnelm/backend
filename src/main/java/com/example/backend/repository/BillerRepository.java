package com.example.backend.repository;

import com.example.backend.model.entity.Biller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillerRepository extends JpaRepository<Biller, Long> {
    @Query("SELECT b FROM Biller b LEFT JOIN FETCH b.bills")
    List<Biller> findAllWithBills();

}
