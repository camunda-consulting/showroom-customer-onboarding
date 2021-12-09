package com.camunda.cloud.workers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.Properties;

@SpringBootApplication
@EnableZeebeClient
public class Email {

    public static void main(String[] args) {
        SpringApplication.run(Email.class, args);
    }

    @Autowired
    private EmailSender emailSender;

    @ZeebeWorker(type = "emailService")
    public void emailService(final JobClient client, final ActivatedJob job) throws Exception {
        // Do the business logic
        System.out.println("About to send email!");

        Map<String, Object> variables = job.getVariablesAsMap();

        String mailBody = (String) variables.get("mailBody");
        String mailSubject = (String) variables.get("mailSubject");
        String mailTo = (String) variables.get("mailTo");

        System.out.println("Body: "+ mailBody);
        System.out.println("Subject: "+  mailSubject);
        System.out.println("To: "+  mailTo);

        emailSender.send(mailBody,mailSubject,mailTo);

        // Probably read some input/output
        variables.put("result", 42);

        // complete the task in the workflow engine
        client.newCompleteCommand(job.getKey())
                .variables(variables)
                .send()
                .exceptionally((throwable -> {throw new RuntimeException("Could not complete job", throwable);}));
    }

}
