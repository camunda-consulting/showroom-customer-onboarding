package com.camunda.demo.customeronboarding.adapter;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.camunda.demo.customeronboarding.model.NewApplication;
import com.camunda.demo.customeronboarding.service.SendMailService;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;

import io.camunda.zeebe.spring.client.annotation.ZeebeVariablesAsType;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;

@Component
public class ConfirmationNotification {

    @Autowired
    private SendMailService sendMailService;

    @Value("${spring.mail.username}")
    private String from;

    @ZeebeWorker(type = "sendCustomerConfirmation", autoComplete = true)
    public NewApplication sendEmail(@ZeebeVariablesAsType NewApplication application)
            throws MessagingException, IOException, GoogleJsonResponseException {
        sendMailService.sendEmail(application);

        return application;
    }
}
