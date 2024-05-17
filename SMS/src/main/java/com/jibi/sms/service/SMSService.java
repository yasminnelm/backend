package com.jibi.sms.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serial;

@Service
@Slf4j
public class SMSService {
   @Value("${TWILIO_ACCOUNT_SID}")
   String ACCOUNT_SID;
    @Value("${TWILIO_AUTH_TOKEN}")
    String AUTH_TOKEN;
    @Value("${TWILIO_OUTGOING_SMS_NUMBER}")
    String OUTGOING_SMS_NUMBER;


   @PostConstruct //initializes
   private void setup()
   {
       Twilio.init(ACCOUNT_SID,AUTH_TOKEN);

   }
   public String sendSMS(String numb, String msg)
    {
       Message message = Message.creator(
               new PhoneNumber(numb),
               new PhoneNumber(OUTGOING_SMS_NUMBER),
               msg).create();

        return message.getStatus().toString();
    }
}
