package com.example.backend.model.mapper;

import com.example.backend.model.dto.AgentDTO;
import com.example.backend.model.dto.ClientDTO;
import com.example.backend.model.dto.FactureDTO;
import com.example.backend.model.entity.Agent;
import com.example.backend.model.entity.Client;
import com.example.backend.model.entity.Creancier;
import com.example.backend.model.entity.Facture;
import com.example.backend.repository.ClientRepository;
import com.example.backend.repository.CreancierRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Mapper {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CreancierRepository creancierRepository;

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


    public Facture fromFactureDTO(FactureDTO factureDTO) {
        Facture facture = new Facture();
        BeanUtils.copyProperties(factureDTO, facture);
        // Load Client and Creancier from the database or another source
        if (factureDTO.getClientId()!= null) {
            Client customer = clientRepository.findById(factureDTO.getClientId()).orElse(null);
            facture.setClient(customer);
        }
        if (factureDTO.getCreancierId() != null) {
            Creancier creancier = creancierRepository.findById(factureDTO.getCreancierId()).orElse(null);
            facture.setCreancier(creancier);
        }
        return facture;
    }

    public FactureDTO fromFacture(Facture facture) {
        FactureDTO factureDTO = new FactureDTO();
        BeanUtils.copyProperties(facture, factureDTO);
        if (facture.getClient() != null) {
            factureDTO.setClientId(facture.getClient().getId());
        }
        if (facture.getCreancier() != null) {
            factureDTO.setCreancierId(facture.getCreancier().getId());
        }
        return factureDTO;
    }

}
