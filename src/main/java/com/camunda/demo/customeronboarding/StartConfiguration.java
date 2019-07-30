package com.camunda.demo.customeronboarding;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.camunda.consulting.simulator.PayloadGenerator;
import com.camunda.consulting.simulator.SimulatorPlugin;
import com.camunda.demo.customeronboarding.DemoData.ContentGenerator;

@Configuration
public class StartConfiguration {
  
  @Bean
  public SimulatorPlugin simulatorPlugin() {
        return new SimulatorPlugin();
  }
  
  @Bean
  public PayloadGenerator generator() {
        return new ContentGenerator();
  }

}
