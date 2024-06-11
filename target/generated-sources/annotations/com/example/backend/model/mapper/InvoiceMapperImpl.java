package com.example.backend.model.mapper;

import com.example.backend.model.dto.InvoiceDTO;
import com.example.backend.model.entity.Biller;
import com.example.backend.model.entity.Client;
import com.example.backend.model.entity.Invoice;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-11T13:29:07+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class InvoiceMapperImpl implements InvoiceMapper {

    @Override
    public InvoiceDTO toInvoiceDTO(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }

        InvoiceDTO invoiceDTO = new InvoiceDTO();

        invoiceDTO.setClientId( invoiceClientId( invoice ) );
        invoiceDTO.setBillerId( invoiceBillerId( invoice ) );
        invoiceDTO.setId( invoice.getId() );
        invoiceDTO.setAmount( invoice.getAmount() );
        invoiceDTO.setIssueDate( invoice.getIssueDate() );
        invoiceDTO.setDeadline( invoice.getDeadline() );
        if ( invoice.getReferencePayment() != null ) {
            invoiceDTO.setReferencePayment( String.valueOf( invoice.getReferencePayment() ) );
        }
        invoiceDTO.setInvoiceStatus( invoice.getInvoiceStatus() );

        return invoiceDTO;
    }

    @Override
    public Invoice toInvoice(InvoiceDTO invoiceDTO) {
        if ( invoiceDTO == null ) {
            return null;
        }

        Invoice invoice = new Invoice();

        invoice.setClient( invoiceDTOToClient( invoiceDTO ) );
        invoice.setBiller( invoiceDTOToBiller( invoiceDTO ) );
        invoice.setId( invoiceDTO.getId() );
        invoice.setAmount( invoiceDTO.getAmount() );
        invoice.setIssueDate( invoiceDTO.getIssueDate() );
        invoice.setDeadline( invoiceDTO.getDeadline() );
        if ( invoiceDTO.getReferencePayment() != null ) {
            invoice.setReferencePayment( Long.parseLong( invoiceDTO.getReferencePayment() ) );
        }
        invoice.setInvoiceStatus( invoiceDTO.getInvoiceStatus() );

        return invoice;
    }

    private Long invoiceClientId(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }
        Client client = invoice.getClient();
        if ( client == null ) {
            return null;
        }
        Long id = client.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long invoiceBillerId(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }
        Biller biller = invoice.getBiller();
        if ( biller == null ) {
            return null;
        }
        Long id = biller.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Client invoiceDTOToClient(InvoiceDTO invoiceDTO) {
        if ( invoiceDTO == null ) {
            return null;
        }

        Client client = new Client();

        client.setId( invoiceDTO.getClientId() );

        return client;
    }

    protected Biller invoiceDTOToBiller(InvoiceDTO invoiceDTO) {
        if ( invoiceDTO == null ) {
            return null;
        }

        Biller biller = new Biller();

        biller.setId( invoiceDTO.getBillerId() );

        return biller;
    }
}
