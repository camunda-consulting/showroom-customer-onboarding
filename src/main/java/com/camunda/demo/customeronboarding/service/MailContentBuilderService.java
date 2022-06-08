package com.camunda.demo.customeronboarding.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.camunda.demo.customeronboarding.model.NewApplication;

@Service
public class MailContentBuilderService {

    @Autowired
    private TemplateEngine templateEngine;

    public String buildConfirmationEmail(NewApplication application, Locale locale) {
        Context context = new Context();

        context.setVariable("applicant", application.getApplicant().getName());
        context.setVariable("contractNumber", application.getContractNumber());
        context.setVariable("applicationNumber", application.getApplicationNumber());
        context.setVariable("product", application.getProduct());
        
        return templateEngine.process("NotifyConfirmation-" + locale.getLanguage(), context);
    }

}