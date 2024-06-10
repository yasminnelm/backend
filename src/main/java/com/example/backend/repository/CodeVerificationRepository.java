package com.example.backend.repository;

import com.example.backend.model.entity.CodeVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
@Repository
public interface CodeVerificationRepository extends JpaRepository<CodeVerification, Long> {
//    CodeVerification findByPhonenumberAndCode(String phonenumber, String code);
//    void deleteByExpirationTimeBefore(LocalDateTime now);
}