package com.example.backend.model.mapper;

import com.example.backend.model.dto.AgentDTO;
import com.example.backend.model.dto.ClientDTO;
import com.example.backend.model.dto.InvoiceDTO;
import com.example.backend.model.entity.Agent;
import com.example.backend.model.entity.Client;
import com.example.backend.model.entity.Biller;
import com.example.backend.model.entity.Invoice;
import com.example.backend.repository.BillerRepository;
import com.example.backend.repository.ClientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Mapper {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BillerRepository billerRepository;

    public Agent fromAgentDTO(AgentDTO agentDTO)
    {
        Agent agent = new Agent();
        //setters pour compte par exemple
        BeanUtils.copyProperties(agentDTO,agent); //les objets de base
        return agent;
    }
    public AgentDTO fromAgent(Agent agent)
    {
        AgentDTO agentDTO = new AgentDTO();
        //setters pour compte par exemple
        BeanUtils.copyProperties(agent,agentDTO); //les objets de base
        return agentDTO;
    }
    public Client fromClientDTO(ClientDTO clientDTO)
    {
        Client client = new Client();
        //setters pour compte par exemple
        BeanUtils.copyProperties(clientDTO,client); //les objets de base
        return client;
    }
    public ClientDTO fromClient(Client customer)
    {
        ClientDTO customerDTO = new ClientDTO();
        //setters pour compte par exemple
        BeanUtils.copyProperties(customer,customerDTO); //les objets de base
        return customerDTO;
    }


    public Invoice fromInvoiceDTO(InvoiceDTO invoiceDTO) {
        Invoice invoice = new Invoice();
        BeanUtils.copyProperties(invoiceDTO, invoice);
        // Load Client and Biller from the database or another source
        if (invoiceDTO.getClientId()!= null) {
            Client customer = clientRepository.findById(invoiceDTO.getClientId()).orElse(null);
            invoice.setClient(customer);
        }
        if (invoiceDTO.getBillerId() != null) {
            Biller biller = billerRepository.findById(invoiceDTO.getBillerId()).orElse(null);
            invoice.setBiller(biller);
        }
        return invoice;
    }

    public InvoiceDTO fromInvoice(Invoice invoice) {
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        BeanUtils.copyProperties(invoice, invoiceDTO);
        if (invoice.getClient() != null) {
            invoiceDTO.setClientId(invoice.getClient().getId());
        }
        if (invoice.getBiller() != null) {
            invoiceDTO.setBillerId(invoice.getBiller().getId());
        }
        return invoiceDTO;
    }

}