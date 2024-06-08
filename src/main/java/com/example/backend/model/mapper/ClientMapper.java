package com.example.backend.model.mapper;

import com.example.backend.model.dto.ClientDTO;
import com.example.backend.model.entity.Client;

public class ClientMapper {

    //Conversion du Client en ClientDTO
    public static ClientDTO toDto(Client client) {
        if (client == null) {
            return null;
        }

        return new ClientDTO(
                client.getId(),
                client.getLastname(),
                client.getFirstname(),
                client.getEmail(),
                client.getPhonenumber(),
                client.getPassword(),
                client.getCinRectoPath(),
                client.getCinVersoPath(),
                client.isFirstLogin());

    }
    //Conversion du ClientDTO en Client
    public static Client toEntity(ClientDTO clientDTO) {
        if(clientDTO==null){
            return null;
        }
        return new Client(
                clientDTO.getId(),
                clientDTO.getLastname(),
                clientDTO.getFirstname(),
                clientDTO.getEmail(),
                clientDTO.getPhonenumber(),
                clientDTO.getPassword(),
                clientDTO.getCinRectoPath(),
                clientDTO.getCinVersoPath(),
                clientDTO.isFirstLogin());

    }

}