package com.camunda.demo.customeronboarding;

import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableZeebeClient
@Deployment(resources = {"static/bpmn/customer_onboarding_en.bpmn", "static/bpmn/document_request_en.bpmn", "static/bpmn/risk_check_en.dmn"})
public class CustomerOnboardingProcessApplication {

  public static void main(String[] args) {
    SpringApplication.run(CustomerOnboardingProcessApplication.class, args);
  }
  
}
