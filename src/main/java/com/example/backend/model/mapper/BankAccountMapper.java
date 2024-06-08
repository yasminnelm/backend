package com.example.backend.model.mapper;

import com.example.backend.model.dto.BankAccountDTO;
import com.example.backend.model.entity.BankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BankAccountMapper {

    BankAccountMapper INSTANCE = Mappers.getMapper(BankAccountMapper.class);

    BankAccount toEntity(BankAccountDTO bankAccountDTO);

    BankAccountDTO toDto(BankAccount bankAccount);
}
