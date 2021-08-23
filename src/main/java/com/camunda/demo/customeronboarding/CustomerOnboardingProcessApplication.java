package com.camunda.demo.customeronboarding;

import org.springframework.beans.factory.annotation.Autowired;
import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeDeployment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableZeebeClient
@ZeebeDeployment(classPathResources = {"static/bpmn/customer_onboarding_en.bpmn", "static/bpmn/document_request_en.bpmn"})
public class CustomerOnboardingProcessApplication {

  public static void main(String[] args) {
    SpringApplication.run(CustomerOnboardingProcessApplication.class, args);
  }
  
}
