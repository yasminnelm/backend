package com.example.backend.model.mapper;

import com.example.backend.model.dto.BillerDTO;
import com.example.backend.model.entity.Bill;
import com.example.backend.model.entity.Biller;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-11T13:29:07+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
public class BillerMapperImpl implements BillerMapper {

    @Override
    public BillerDTO toDto(Biller biller) {
        if ( biller == null ) {
            return null;
        }

        BillerDTO billerDTO = new BillerDTO();

        billerDTO.setId( biller.getId() );
        billerDTO.setName( biller.getName() );
        billerDTO.setBillCategory( biller.getBillCategory() );
        List<Bill> list = biller.getBills();
        if ( list != null ) {
            billerDTO.setBills( new ArrayList<Bill>( list ) );
        }

        return billerDTO;
    }

    @Override
    public Biller toEntity(BillerDTO billerDTO) {
        if ( billerDTO == null ) {
            return null;
        }

        Biller biller = new Biller();

        biller.setId( billerDTO.getId() );
        biller.setName( billerDTO.getName() );
        biller.setBillCategory( billerDTO.getBillCategory() );
        List<Bill> list = billerDTO.getBills();
        if ( list != null ) {
            biller.setBills( new ArrayList<Bill>( list ) );
        }

        return biller;
    }
}
