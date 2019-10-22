package com.camunda.demo.customeronboarding;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
@EnableProcessApplication("customer-onboarding")
public class CustomerOnboardingProcessApplication {

  public static void main(String[] args) {
    SpringApplication.run(CustomerOnboardingProcessApplication.class, args);
  }
  
}
