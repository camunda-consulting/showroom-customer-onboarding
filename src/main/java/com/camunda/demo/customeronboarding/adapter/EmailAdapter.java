package com.camunda.demo.customeronboarding.adapter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camunda.demo.customeronboarding.ProcessConstants;
import com.camunda.demo.customeronboarding.model.NewApplication;

@Component
public class EmailAdapter implements JavaDelegate {
  
  @Autowired
  public EmailAdapter (SendEmailService sendEmailService) {
    this.sendEmailService = sendEmailService;
  }
  
  private SendEmailService sendEmailService;

  @Override
  public void execute(DelegateExecution execution) throws Exception {

    // data input mapping
    String mailtext = (String) execution.getVariable("mailBody");
    String subject = (String) execution.getVariable("mailSubject");
    NewApplication application = (NewApplication) execution.getVariable(ProcessConstants.VAR_NAME_application);
    String email = application.getApplicant().getEmail();

    // service call
    sendEmailService.sendEmail(email, mailtext, subject);
  }
}
