package com.example.backend.service;//package com.example.backend.service;
//
//import com.example.backend.model.dto.ClientDTO;
//import com.example.backend.model.entity.Client;
//
//import com.example.backend.model.mapper.ClientMapper;
//import com.example.backend.repository.ClientRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class ClientService {
//    private final ClientRepository clientRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final MailPasswordService mailPasswordService;
//
//    @Autowired
//    public ClientService(ClientRepository clientRepository, PasswordEncoder passwordEncoder, MailPasswordService mailPasswordService) {
//        this.clientRepository = clientRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.mailPasswordService = mailPasswordService;
//    }
//    public ClientDTO createClient(String nom, String prenom,String email,
//                                  String telephone, byte[] cinRectoPath,
//                                  byte[] cinVersoPath) {
//
//        telephone = "+" + telephone;
//        // Confirmation du numéro de téléphone
//        if (!telephone.matches("^\\+212[6-7][0-9]{8}$")) {
//            throw new IllegalArgumentException("Phone number does not match the Moroccan form");
//        }
//
//
//
//        String password = mailPasswordService.generateDefaultPassword();
//
//        Client client = new Client();
//        client.setNom(nom);
//        client.setPrenom(prenom);
//        client.setEmail(email);
//        client.setTelephone(telephone);
//        client.setCinRectoPath(cinRectoPath);
//        client.setCinVersoPath(cinVersoPath);
//        client.setPassword(passwordEncoder.encode(password));
//
//        Client savedClient = clientRepository.save(client);
//        mailPasswordService.sendPasswordByEmail(email, password);
//
//        return ClientMapper.toDto(savedClient);
//    }
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


import com.example.backend.model.entity.Client;
import com.example.backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;


    @Autowired
    private CMIService cmiService;
    @Autowired
    private MailPasswordService mailPasswordService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Client registerClient(Client client) {
        if (!cmiService.isResponseFavorable()) {
            throw new IllegalArgumentException("CMI response is not favorable");
        }
        String tempPassword = mailPasswordService.generateDefaultPassword();
        client.setPassword(passwordEncoder.encode(tempPassword));  // Hashing du mot de passe temporaire
        return clientRepository.save(client);
    }

    public Optional<Client> verifyPassword(String email, String password) {
        Optional<Client> clientOpt = Optional.ofNullable(clientRepository.findClientByEmail(email));

        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();
            if (passwordEncoder.matches(password, client.getPassword())) {
                return Optional.of(client);
            }
        }
        return Optional.empty();
    }

}
