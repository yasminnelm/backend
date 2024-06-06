package com.example.backend.service;

import com.example.backend.model.dto.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CMIService {

    private final RestTemplate restTemplate;
    private final String cmiUrl;

    @Autowired
    public CMIService(RestTemplate restTemplate, @Value("${cmi.service.url}") String cmiUrl) {
        this.restTemplate = restTemplate;
        this.cmiUrl = cmiUrl;
    }

    public boolean createAccountWithCMI(ClientDTO clientDTO) {
        String url = cmiUrl + "/createAccount";
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, clientDTO, String.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}