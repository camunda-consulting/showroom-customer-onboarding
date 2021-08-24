package com.camunda.demo.customeronboarding;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableProcessApplication("customer-onboarding")
public class CustomerOnboardingProcessApplication {

  public static void main(String[] args) {
    SpringApplication.run(CustomerOnboardingProcessApplication.class, args);
  }
  
}
