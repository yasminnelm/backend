package com.example.backend.model.mapper;

import com.example.backend.model.dto.AccountOperationDTO;
import com.example.backend.model.entity.AccountOperation;
import com.example.backend.model.entity.BankAccount;
import com.example.backend.model.enumeration.OperationType;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-11T13:29:07+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
public class AccountOperationMapperImpl implements AccountOperationMapper {

    @Override
    public AccountOperation toEntity(AccountOperationDTO accountOperationDTO) {
        if ( accountOperationDTO == null ) {
            return null;
        }

        AccountOperation accountOperation = new AccountOperation();

        accountOperation.setBankAccount( accountOperationDTOToBankAccount( accountOperationDTO ) );
        accountOperation.setId( accountOperationDTO.getId() );
        accountOperation.setOperationDate( accountOperationDTO.getOperationDate() );
        accountOperation.setAmount( accountOperationDTO.getAmount() );
        if ( accountOperationDTO.getType() != null ) {
            accountOperation.setType( Enum.valueOf( OperationType.class, accountOperationDTO.getType() ) );
        }
        accountOperation.setDescription( accountOperationDTO.getDescription() );

        return accountOperation;
    }

    @Override
    public AccountOperationDTO toDto(AccountOperation accountOperation) {
        if ( accountOperation == null ) {
            return null;
        }

        AccountOperationDTO accountOperationDTO = new AccountOperationDTO();

        accountOperationDTO.setBankAccountId( accountOperationBankAccountId( accountOperation ) );
        accountOperationDTO.setId( accountOperation.getId() );
        accountOperationDTO.setOperationDate( accountOperation.getOperationDate() );
        accountOperationDTO.setAmount( accountOperation.getAmount() );
        if ( accountOperation.getType() != null ) {
            accountOperationDTO.setType( accountOperation.getType().name() );
        }
        accountOperationDTO.setDescription( accountOperation.getDescription() );

        return accountOperationDTO;
    }

    protected BankAccount accountOperationDTOToBankAccount(AccountOperationDTO accountOperationDTO) {
        if ( accountOperationDTO == null ) {
            return null;
        }

        BankAccount bankAccount = new BankAccount();

        bankAccount.setId( accountOperationDTO.getBankAccountId() );

        return bankAccount;
    }

    private Long accountOperationBankAccountId(AccountOperation accountOperation) {
        if ( accountOperation == null ) {
            return null;
        }
        BankAccount bankAccount = accountOperation.getBankAccount();
        if ( bankAccount == null ) {
            return null;
        }
        Long id = bankAccount.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
