package com.jibi.sms.model;

import lombok.Data;

@Data
public class SMSSendRequest {
    private String smsNumberReceiver,msgSMS;
}
