package com.example.backend.service;

import com.example.backend.model.dto.CodeDTO;
import com.example.backend.model.entity.CodeVerification;
import com.example.backend.repository.CodeVerificationRepository;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;
import com.vonage.client.VonageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class SMSService {

    @Autowired
    private VonageClient vonageClient;

    @Autowired
    private CodeVerificationRepository codeVerificationRepository;

    public String sendCode(CodeDTO codeDTO) {
        String code = generateCode();
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(3);

        CodeVerification codeVerification = new CodeVerification();
        codeVerification.setPhonenumber(codeDTO.getPhonenumber());
        codeVerification.setCode(code);
        codeVerification.setExpirationTime(expirationTime);

        codeVerificationRepository.save(codeVerification);

        TextMessage message = new TextMessage(
                "JIBI",
                codeDTO.getPhonenumber(),
                "Your verification code is : " + code
        );

        SmsSubmissionResponse response = vonageClient.getSmsClient().submitMessage(message);

        response.getMessages().forEach(submission -> {
            if (submission.getStatus() != MessageStatus.OK) {
                throw new RuntimeException("Message failed with error: " + submission.getErrorText());
            }
        });

        return code;
    }

    private String generateCode() {
        Random random = new Random();
        int code = 1000 + random.nextInt(9000);
        return String.valueOf(code);
    }
}
