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
                client.getNom(),
                client.getPrenom(),
                client.getTelephone(),
                client.getEmail(),
                client.getCinRectoPath(),
                client.getCinVersoPath(),
                client.getCompteType(),
                client.getPlafond()
        );
    }
    //Conversion du ClientDTO en Client
    public static Client toEntity(ClientDTO clientDTO) {
        if(clientDTO==null){
            return null;
        }
        return new Client(
                clientDTO.getId(),
                clientDTO.getNom(),
                clientDTO.getPrenom(),
                clientDTO.getEmail(),
                clientDTO.getTelephone(),
                clientDTO.getCinRectoPath(),
                clientDTO.getCinVersoPath(),
                clientDTO.getCompteType(),
                clientDTO.getPlafond());
    }

}
