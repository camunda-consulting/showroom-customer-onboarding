package com.camunda.demo.customeronboarding.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Locale;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.camunda.demo.customeronboarding.model.NewApplication;
import com.camunda.demo.customeronboarding.utils.DriveUtils;
import com.camunda.demo.customeronboarding.utils.GmailUtils;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;

@Service
public class SendMailService {
    private final static Logger LOG = LoggerFactory.getLogger(SendMailService.class);

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    public void sendEmail(NewApplication application) throws MessagingException, IOException {

        // Create the gmail API client
        Gmail service = GmailUtils.gmail();
        
        //get attachment
        String attachment = "Contract_"+application.getContractNumber()+".pdf";
        File file = DriveUtils.downloadFromDrive(application.getContractDriveId(), attachment);

        //Build message
        MimeMessage mimeMessage = buildMimeMessage(application, file);
        
        Message gmailMessage = convertToGmailMessage(mimeMessage);

        try {
            // Create send message
            gmailMessage = service.users().messages().send("me", gmailMessage).execute();
        } catch (GoogleJsonResponseException e) {
            LOG.error("Unable to send message: ", e);
            throw e;
        }
        finally {

            try {
                Files.delete(file.toPath());
            } catch (IOException e) {
                LOG.error("Error deleting file", e);
            }
        }
    }
    
    private MimeMessage buildMimeMessage(NewApplication application, File attachment) throws MessagingException, IOException {

        //create MimeMessage
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage email = new MimeMessage(session);
        MimeMessageHelper messageHelper = new MimeMessageHelper(email, true);
        messageHelper.setFrom(from);
        messageHelper.setTo(application.getApplicant().getEmail());
        messageHelper.setSubject("Welcome at Camunbankia, "+application.getApplicant().getName()+"!");
        messageHelper.setText(buildConfirmationEmail(application, Locale.ENGLISH), true);
        messageHelper.addAttachment(attachment.getName(), attachment);

        return messageHelper.getMimeMessage();
    }
    
    private Message convertToGmailMessage(MimeMessage mimeMessage) throws IOException, MessagingException {
        // Encode and wrap the MIME message into a gmail message
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        mimeMessage.writeTo(buffer);
        byte[] rawMessageBytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
        Message message = new Message();
        message.setRaw(encodedEmail);
        return message;
    }
    

    public String buildConfirmationEmail(NewApplication application, Locale locale) {
        Context context = new Context();

        context.setVariable("applicant", application.getApplicant().getName());
        context.setVariable("contractNumber", application.getContractNumber());
        context.setVariable("applicationNumber", application.getApplicationNumber());
        context.setVariable("product", application.getProduct());
        
        return templateEngine.process("NotifyConfirmation-" + locale.getLanguage(), context);
    }
}
