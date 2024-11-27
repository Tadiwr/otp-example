package com.tadiwa.otp_example.providers.email;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;

@Component
public interface EmailProvider {
    
    public void sendEmail(SendEmailDTO sendEmailDTO) throws MessagingException, UnsupportedEncodingException;

}
