package com.tadiwa.otp_example.providers.email;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailProviderImpl implements EmailProvider {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(SendEmailDTO sendEmailDTO) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("codapt@outlook.com", "Java OPT Example");
        helper.setTo(new InternetAddress(sendEmailDTO.getRecepient()));
        helper.setSubject(sendEmailDTO.getSubject());
        helper.setText(buildMessage(sendEmailDTO.getMessage()), sendEmailDTO.isHtml());
        helper.setValidateAddresses(true);

        javaMailSender.send(message);
        
    }

    private String buildMessage(String emailBody) {
        return getMailHeader() + emailBody;
    }

    private String getMailHeader() {
        return """
            <div class="email-header" >
                <h1 class="heading" >Java OTP Example</h1>
                <p class="tagline" >Your Security Matters</p>
            </div>

            <style>
                .email-header {
                    background-color: rgb(15, 15, 15);
                    margin: 0px;
                    padding: 15px;
                    border-radius: 10px;
                }

                .heading {
                    font-weight:900;
                    margin: 0px;
                    padding: 0px;
                    margin-bottom: 0px;
                    color: white;
                }

                .tagline {
                    font-weight: 600;
                    color: rgb(153, 153, 153);
                }
            </style>
        """;
    }
    
}
