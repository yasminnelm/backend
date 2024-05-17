package com.jibi.sms.controller;

import com.jibi.sms.model.SMSSendRequest;
import com.jibi.sms.service.SMSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class SMSRestController {
   @Autowired
    SMSService smsService;
    @PostMapping("/prcSMS")
    public String processSMS(@RequestBody SMSSendRequest sendRequest)
    {
       log.info("Processing SMS "+sendRequest.toString());
        return smsService.sendSMS(sendRequest.getSmsNumberReceiver(), sendRequest.getMsgSMS());
    }
}
