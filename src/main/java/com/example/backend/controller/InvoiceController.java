package com.example.backend.controller;

import com.example.backend.model.dto.InvoiceDTO;
import com.example.backend.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@CrossOrigin("*")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<InvoiceDTO> createInvoice(
            @RequestParam Long billerId,
            @RequestParam Long clientId,
            @RequestParam Double amount,
            @RequestParam String description) {
        InvoiceDTO invoiceDTO = invoiceService.createInvoice(billerId, clientId, amount,description);
        return ResponseEntity.ok(invoiceDTO);
    }

    @GetMapping("/pending/{clientId}")
    public ResponseEntity<List<InvoiceDTO>> getPendingInvoicesByClient(@PathVariable Long clientId) {
        List<InvoiceDTO> pendingInvoices = invoiceService.getPendingInvoicesByClient(clientId);
        return ResponseEntity.ok(pendingInvoices);
    }
}
