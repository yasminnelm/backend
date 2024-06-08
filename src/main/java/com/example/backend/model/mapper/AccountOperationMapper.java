package com.example.backend.model.mapper;

import com.example.backend.model.dto.AccountOperationDTO;
import com.example.backend.model.entity.AccountOperation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountOperationMapper {

    AccountOperationMapper INSTANCE = Mappers.getMapper(AccountOperationMapper.class);

    AccountOperation toEntity(AccountOperationDTO accountOperationDTO);

    AccountOperationDTO toDto(AccountOperation accountOperation);
}
