package com.camunda.cloud.workers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
@Scope("singleton")
public class EmailSender {

    @Value("${email.username}")
    private String username;
    @Value("${email.password}")
    private String password;
    @Value("${email.smtp}")
    private String smtp;
    @Value("${email.port}")
    private String port;
    @Value("${email.auth}")
    private String auth;
    @Value("${email.starttls_enable}")
    private String starttls_enable;
    @Value("${email.from}")
    private String from;

    public void send(String mailBody, String mailSubject,String mailTo) throws Exception
    {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", smtp);
        prop.put("mail.smtp.port", port);
        prop.put("mail.smtp.auth", auth);
        prop.put("mail.smtp.starttls.enable", starttls_enable);

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(mailTo)
        );
        message.setSubject(mailSubject);
        message.setContent(mailBody, "text/html");

        Transport.send(message);

        System.out.println("Done");
    }

}

