package com.example.backend.model.mapper;

import com.example.backend.model.dto.ClientBankAccountDTO;
import com.example.backend.model.entity.ClientBankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientBankAccountMapper {

    ClientBankAccountMapper INSTANCE = Mappers.getMapper(ClientBankAccountMapper.class);

    ClientBankAccount toEntity(ClientBankAccountDTO clientBankAccountDTO);

    ClientBankAccountDTO toDto(ClientBankAccount clientBankAccount);
}
