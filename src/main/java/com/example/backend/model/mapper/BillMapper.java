package com.example.backend.model.mapper;

import com.example.backend.model.dto.BillDTO;
import com.example.backend.model.entity.Bill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BillMapper {

    BillMapper INSTANCE = Mappers.getMapper(BillMapper.class);


//    @Mapping(source = "biller.id", target = "billerId")
    BillDTO toDto(Bill bill);

//    @Mapping(source = "billerId", target = "biller.id")
    Bill toEntity(BillDTO billDTO);
}