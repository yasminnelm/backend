package com.example.backend.model.mapper;

import com.example.backend.model.dto.BillerDTO;
import com.example.backend.model.entity.Biller;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BillerMapper {

    BillerMapper INSTANCE = Mappers.getMapper(BillerMapper.class);

    @Mapping(source = "bills", target = "billIds")
    @Mapping(source = "invoices", target = "invoiceIds")
    @Mapping(source = "professionalBankAccount.id", target = "professionalBankAccountId")
    BillerDTO toDto(Biller biller);

    Biller toEntity(BillerDTO billerDTO);
}
