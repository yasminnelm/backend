package com.example.backend.service;

import com.example.backend.model.dto.ClientDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CMIService {

    private final RestTemplate restTemplate;

    @Value("${cmi.service.url}")
    private String cmiUrl;

    @Autowired
    public CMIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean isResponseFavorable(ClientDTO clientDTO) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<ClientDTO> requestEntity = new HttpEntity<>(clientDTO, headers);
            ResponseEntity<CmiResponse> response = restTemplate.postForEntity(cmiUrl, requestEntity, CmiResponse.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody().isFavorable();
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
