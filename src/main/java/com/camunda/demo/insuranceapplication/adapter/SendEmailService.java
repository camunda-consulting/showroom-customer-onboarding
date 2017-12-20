package com.camunda.demo.insuranceapplication.adapter;

import java.io.IOException;
import java.net.InetAddress;

import javax.mail.MessagingException;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendEmailService {
  Logger LOG = LoggerFactory.getLogger(SendEmailService.class);

  public void sendEmail(String toEmail, String mailtext, String subject) throws EmailException {
    Email email = new SimpleEmail();
    email.setHostName("mail.camunda.com");
    email.setAuthentication("demo@mx.camunda.com", "28484234386345");
    email.setFrom("demo@camunda.com");
    email.setCharset("utf-8");
    email.setSubject(subject);
    email.setMsg(mailtext);
    
    try {
      email.addTo(toEmail);
    } catch (RuntimeException e) {
      // Here we get malformed e-mail exceptions. Nice for showcasing fixing incidents, so let it crash!
      throw e;
    }

    boolean success = false;
    try {
        email.send();
        success = true;
    } catch (Exception e) {
      // no internet or whatever
    }

    if (!success) {
      // ok, sending mail impossible - print it out
      try {
        email.buildMimeMessage();
      } catch (Exception e) {
        // meh.
      }
      try {
        LOG.info(email.getMimeMessage().getContent().toString());
      } catch (IOException | MessagingException e) {
        // whatever.
      }
    }
  }

}
