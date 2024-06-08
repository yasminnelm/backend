package com.example.backend.model.mapper;

import com.example.backend.model.dto.ProfessionalBankAccountDTO;
import com.example.backend.model.entity.ProfessionalBankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfessionalBankAccountMapper {

    ProfessionalBankAccountMapper INSTANCE = Mappers.getMapper(ProfessionalBankAccountMapper.class);

    ProfessionalBankAccount toEntity(ProfessionalBankAccountDTO professionalBankAccountDTO);

    ProfessionalBankAccountDTO toDto(ProfessionalBankAccount professionalBankAccount);
}
