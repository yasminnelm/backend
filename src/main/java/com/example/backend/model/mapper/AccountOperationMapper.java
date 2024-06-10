package com.example.backend.model.mapper;

import com.example.backend.model.dto.AccountOperationDTO;
import com.example.backend.model.entity.AccountOperation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountOperationMapper {

    AccountOperationMapper INSTANCE = Mappers.getMapper(AccountOperationMapper.class);
    @Mapping(source = "bankAccountId", target = "bankAccount.id")
    AccountOperation toEntity(AccountOperationDTO accountOperationDTO);
    @Mapping(source = "bankAccount.id", target = "bankAccountId")
    AccountOperationDTO toDto(AccountOperation accountOperation);
}



