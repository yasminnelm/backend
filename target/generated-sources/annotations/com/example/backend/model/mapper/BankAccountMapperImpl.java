package com.example.backend.model.mapper;

import com.example.backend.model.dto.BankAccountDTO;
import com.example.backend.model.entity.BankAccount;
import com.example.backend.model.enumeration.AccountStatus;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-11T13:29:07+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
public class BankAccountMapperImpl implements BankAccountMapper {

    @Override
    public BankAccount toEntity(BankAccountDTO bankAccountDTO) {
        if ( bankAccountDTO == null ) {
            return null;
        }

        BankAccount bankAccount = new BankAccount();

        bankAccount.setId( bankAccountDTO.getId() );
        bankAccount.setBalance( bankAccountDTO.getBalance() );
        bankAccount.setAccountNumber( bankAccountDTO.getAccountNumber() );
        bankAccount.setCreatedAt( bankAccountDTO.getCreatedAt() );
        if ( bankAccountDTO.getStatus() != null ) {
            bankAccount.setStatus( Enum.valueOf( AccountStatus.class, bankAccountDTO.getStatus() ) );
        }
        bankAccount.setAccountType( bankAccountDTO.getAccountType() );

        return bankAccount;
    }

    @Override
    public BankAccountDTO toDto(BankAccount bankAccount) {
        if ( bankAccount == null ) {
            return null;
        }

        BankAccountDTO bankAccountDTO = new BankAccountDTO();

        bankAccountDTO.setId( bankAccount.getId() );
        bankAccountDTO.setAccountNumber( bankAccount.getAccountNumber() );
        bankAccountDTO.setAccountType( bankAccount.getAccountType() );
        bankAccountDTO.setBalance( bankAccount.getBalance() );
        bankAccountDTO.setCreatedAt( bankAccount.getCreatedAt() );
        if ( bankAccount.getStatus() != null ) {
            bankAccountDTO.setStatus( bankAccount.getStatus().name() );
        }

        return bankAccountDTO;
    }
}
