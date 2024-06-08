package com.example.backend.model.mapper;

import com.example.backend.model.dto.ClientDTO;
import com.example.backend.model.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    Client toEntity(ClientDTO clientDTO);

    ClientDTO toDto(Client client);
}
