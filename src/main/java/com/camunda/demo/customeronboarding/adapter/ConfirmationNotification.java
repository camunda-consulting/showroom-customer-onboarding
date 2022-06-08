package com.camunda.demo.customeronboarding.adapter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import org.springframework.stereotype.Component;

import com.camunda.demo.customeronboarding.model.NewApplication;
import com.camunda.demo.customeronboarding.service.MailContentBuilderService;
import com.camunda.demo.customeronboarding.utils.DriveUtils;
import com.camunda.demo.customeronboarding.utils.GmailUtils;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeVariablesAsType;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;

@Component
public class ConfirmationNotification {

    private final static Logger LOG = LoggerFactory.getLogger(ConfirmationNotification.class);

    @Autowired
    private MailContentBuilderService mailContentBuilderService;

    @Value("${spring.mail.username}")
    private String from;

    @ZeebeWorker(type = "sendCustomerConfirmation")
    public void sendEmail(@ZeebeVariablesAsType NewApplication application, JobClient client, ActivatedJob job)
            throws MessagingException, IOException {

        // Create the gmail API client
        Gmail service = GmailUtils.gmail();

        //get attachment
        String attachment = "Contract_"+application.getContractNumber()+".pdf";
        File file = DriveUtils.downloadFromDrive(application.getContractDriveId(), attachment);

        //create MimeMessage
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage email = new MimeMessage(session);
        MimeMessageHelper messageHelper = new MimeMessageHelper(email, true);
        messageHelper.setFrom(from);
        messageHelper.setTo(application.getApplicant().getEmail());
        messageHelper.setSubject("Welcome at Camunbankia, "+application.getApplicant().getName()+"!");
        messageHelper.setText(mailContentBuilderService.buildConfirmationEmail(application, Locale.ENGLISH), true);
        messageHelper.addAttachment(attachment, file);

        // Encode and wrap the MIME message into a gmail message
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        messageHelper.getMimeMessage().writeTo(buffer);
        byte[] rawMessageBytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
        Message message = new Message();
        message.setRaw(encodedEmail);

        try {
            // Create send message
            message = service.users().messages().send("me", message).execute();

            client.newCompleteCommand(job.getKey()).send().exceptionally((throwable -> {
                throw new RuntimeException("Could not complete job", throwable);
            }));
        } catch (GoogleJsonResponseException e) {
            LOG.error("Unable to send message: ", e);
            throw e;
        }
        finally {

            try {
                Files.delete(Paths.get(attachment));
            } catch (IOException e) {
                LOG.error("Error deleting file", e);
            }
        }
    }

}
