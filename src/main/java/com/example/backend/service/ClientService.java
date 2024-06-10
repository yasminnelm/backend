package com.example.backend.service;//package com.example.backend.service;

//
//    public List<ClientDTO> findAllClients() {
//        return clientRepository.findAll().stream()
//                .map(ClientMapper::toDto)
//                .collect(Collectors.toList());
//    }
//
//    public Optional<ClientDTO> getClientById(Long id) {
//        return clientRepository.findById(id)
//                .map(ClientMapper::toDto);
//    }
//
////    public Optional<ClientDTO> getClientByEmail(String email) {
////        return ClientMapper.toDto(clientRepository.findClientByEmail(email));
////
////    }
//
//    public ClientDTO updateClient(Long id, ClientDTO updatedClientDTO) {
//        Optional<Client> optionalClient = clientRepository.findById(id);
//        if (optionalClient.isPresent()) {
//            Client client = optionalClient.get();
//            client.setNom(updatedClientDTO.getNom());
//            client.setPrenom(updatedClientDTO.getPrenom());
//            client.setTelephone(updatedClientDTO.getTelephone());
//            client.setEmail(updatedClientDTO.getEmail());
//            client.setCinRectoPath(updatedClientDTO.getCinRectoPath());
//            client.setCinVersoPath(updatedClientDTO.getCinVersoPath());
//
//            Client updatedClient = clientRepository.save(client);
//            return ClientMapper.toDto(updatedClient);
//        } else {
//            throw new IllegalArgumentException("Client not found");
//        }
//    }
//
//    public void deleteClient(Long id) {
//        clientRepository.deleteById(id);
//    }
//}


import com.example.backend.model.dto.ClientDTO;
import com.example.backend.model.entity.BankAccount;
import com.example.backend.model.entity.Client;
import com.example.backend.model.mapper.ClientMapper;
import com.example.backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CMIService cmiService = new CMIService(restTemplate);
    @Autowired
    private MailPasswordService mailPasswordService;

//    @Autowired
//    private PasswordEncoder passwordEncoder ;


    public List<ClientDTO> findAllClients() {
        return clientRepository.findAll().stream()
                .map(ClientMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }


    public Client registerClient(ClientDTO clientDTO) {
        if (!cmiService.isResponseFavorable(clientDTO)) {
            throw new IllegalArgumentException("CMI response is not favorable");
        }
        String tempPassword = mailPasswordService.generateDefaultPassword();
        clientDTO.setPassword(tempPassword);
        return clientRepository.save(ClientMapper.INSTANCE.toEntity(clientDTO));
    }
    public Client updateClient(Client existingClient, BankAccount bankAccount) {


             existingClient.setBankAccount(bankAccount);
            return clientRepository.save(existingClient);
        }
    public void deleteClientById(Long id){
        clientRepository.deleteById(id);
    }
}
