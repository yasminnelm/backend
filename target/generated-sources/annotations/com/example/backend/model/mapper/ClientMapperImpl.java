package com.example.backend.model.mapper;

import com.example.backend.model.dto.ClientDTO;
import com.example.backend.model.entity.Client;
import java.util.Arrays;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-11T13:29:07+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
public class ClientMapperImpl implements ClientMapper {

    @Override
    public Client toEntity(ClientDTO clientDTO) {
        if ( clientDTO == null ) {
            return null;
        }

        Client client = new Client();

        client.setId( clientDTO.getId() );
        client.setLastname( clientDTO.getLastname() );
        client.setFirstname( clientDTO.getFirstname() );
        client.setEmail( clientDTO.getEmail() );
        client.setPhonenumber( clientDTO.getPhonenumber() );
        client.setPassword( clientDTO.getPassword() );
        byte[] cinRectoPath = clientDTO.getCinRectoPath();
        if ( cinRectoPath != null ) {
            client.setCinRectoPath( Arrays.copyOf( cinRectoPath, cinRectoPath.length ) );
        }
        byte[] cinVersoPath = clientDTO.getCinVersoPath();
        if ( cinVersoPath != null ) {
            client.setCinVersoPath( Arrays.copyOf( cinVersoPath, cinVersoPath.length ) );
        }
        client.setFirstLogin( clientDTO.isFirstLogin() );

        return client;
    }

    @Override
    public ClientDTO toDto(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setId( client.getId() );
        clientDTO.setLastname( client.getLastname() );
        clientDTO.setFirstname( client.getFirstname() );
        clientDTO.setEmail( client.getEmail() );
        clientDTO.setPhonenumber( client.getPhonenumber() );
        clientDTO.setPassword( client.getPassword() );
        byte[] cinRectoPath = client.getCinRectoPath();
        if ( cinRectoPath != null ) {
            clientDTO.setCinRectoPath( Arrays.copyOf( cinRectoPath, cinRectoPath.length ) );
        }
        byte[] cinVersoPath = client.getCinVersoPath();
        if ( cinVersoPath != null ) {
            clientDTO.setCinVersoPath( Arrays.copyOf( cinVersoPath, cinVersoPath.length ) );
        }
        clientDTO.setFirstLogin( client.isFirstLogin() );

        return clientDTO;
    }
}
