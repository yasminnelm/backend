package com.example.backend.model.mapper;

import com.example.backend.model.dto.InvoiceDTO;
import com.example.backend.model.entity.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring",uses = {ClientMapper.class, BillerMapper.class})
@Service
public interface InvoiceMapper {
    InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);

    @Mappings({
            @Mapping(source = "client.id", target = "clientId"),
            @Mapping(source = "biller.id", target = "billerId")
    })
    InvoiceDTO toInvoiceDTO(Invoice invoice);

    @Mappings({
            @Mapping(source = "clientId", target = "client.id"),
            @Mapping(source = "billerId", target = "biller.id")
    })
    Invoice toInvoice(InvoiceDTO invoiceDTO);
}

