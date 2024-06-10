package com.example.backend.repository;

import com.example.backend.model.entity.Client;
import com.example.backend.model.entity.Invoice;
import com.example.backend.model.enumeration.InvoiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
    List<Invoice> findByClientAndInvoiceStatus(Client client, InvoiceStatus invoiceStatus);
}
