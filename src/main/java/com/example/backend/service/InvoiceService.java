package com.example.backend.service;

import com.example.backend.model.dto.InvoiceDTO;
import com.example.backend.model.entity.Biller;
import com.example.backend.model.entity.Client;
import com.example.backend.model.entity.Invoice;
import com.example.backend.model.enumeration.InvoiceStatus;
import com.example.backend.model.mapper.InvoiceMapper;
import com.example.backend.repository.BillerRepository;
import com.example.backend.repository.ClientRepository;
import com.example.backend.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BillerRepository billerRepository;

    @Autowired
    private InvoiceMapper invoiceMapper;

    public InvoiceDTO createInvoice(Long billerId, Long clientId, Double amount,String description) {
        Optional<Biller> billerOpt = billerRepository.findById(billerId);
        Optional<Client> clientOpt = clientRepository.findById(clientId);

        if (billerOpt.isPresent() && clientOpt.isPresent()) {
            Biller biller = billerOpt.get();
            Client client = clientOpt.get();

            Invoice invoice = new Invoice();
            invoice.setBiller(biller);
            invoice.setClient(client);
            invoice.setAmount(amount);
            invoice.setIssueDate(new Date());
            invoice.setDeadline(calculateDeadline(new Date()));
            invoice.setInvoiceStatus(InvoiceStatus.PENDING);
            invoice.setReferencePayment(generateAccountNumber());
            invoice.setDescription(description);
            Invoice savedInvoice = invoiceRepository.save(invoice);
            return invoiceMapper.toInvoiceDTO(savedInvoice);
        } else {
            throw new IllegalArgumentException("Invalid biller or client ID");
        }
    }

    public List<InvoiceDTO> getPendingInvoicesByClient(Long clientId) {
        Optional<Client> clientOpt = clientRepository.findById(clientId);
        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();
            List<Invoice> pendingInvoices = invoiceRepository.findByClientAndInvoiceStatus(client, InvoiceStatus.PENDING);
            return pendingInvoices.stream()
                    .map(invoiceMapper::toInvoiceDTO)
                    .collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("Invalid client ID");
        }
    }

    private Date calculateDeadline(Date issueDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(issueDate);
        calendar.add(Calendar.DAY_OF_YEAR, 3);
        return calendar.getTime();
    }

//    private String generateReferencePayment() {
//        return UUID.randomUUID().toString();
//    }
    private long generateAccountNumber() {
        Random random = new Random();
        return 10000000000L + (long) (random.nextDouble() * 90000000000L);
    }
}
