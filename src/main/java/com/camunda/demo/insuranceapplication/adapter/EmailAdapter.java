package com.camunda.demo.insuranceapplication.adapter;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.camunda.demo.insuranceapplication.ProcessConstants;
import com.camunda.demo.insuranceapplication.model.Application;

public class EmailAdapter implements JavaDelegate {

  @Named("emailAdapter")
  @Override
  public void execute(DelegateExecution execution) throws Exception {
    // get service reference
	SendEmailService sendEmailService = new SendEmailService();
	  
	// data input mapping
	String mailtext = (String) execution.getVariable("mailBody");
    String subject = (String) execution.getVariable("mailSubject");
    Application application = (Application) execution.getVariable(ProcessConstants.VAR_NAME_application);
    String email = application.getApplicant().getEmail();
    
    // service call
    sendEmailService.sendEmail(email, mailtext, subject);
  }

}
