package com.example.backend.model.mapper;

import com.example.backend.model.dto.BillDTO;
import com.example.backend.model.entity.Bill;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-11T13:29:07+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
public class BillMapperImpl implements BillMapper {

    @Override
    public BillDTO toDto(Bill bill) {
        if ( bill == null ) {
            return null;
        }

        BillDTO billDTO = new BillDTO();

        billDTO.setId( bill.getId() );
        billDTO.setCodeCreance( bill.getCodeCreance() );
        billDTO.setName( bill.getName() );
        billDTO.setPrice( bill.getPrice() );

        return billDTO;
    }

    @Override
    public Bill toEntity(BillDTO billDTO) {
        if ( billDTO == null ) {
            return null;
        }

        Bill bill = new Bill();

        bill.setId( billDTO.getId() );
        bill.setCodeCreance( billDTO.getCodeCreance() );
        bill.setName( billDTO.getName() );
        bill.setPrice( billDTO.getPrice() );

        return bill;
    }
}
