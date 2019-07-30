package com.camunda.demo.customeronboarding;

import javax.annotation.PostConstruct;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.camunda.bpm.spring.boot.starter.event.ProcessApplicationStartedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import com.camunda.consulting.simulator.PayloadGenerator;
import com.camunda.consulting.simulator.SimulatorPlugin;
import com.camunda.demo.customeronboarding.DemoData.ContentGenerator;

@Configuration
public class StartConfiguration {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(StartConfiguration.class);

  @Autowired
  private static ProcessEngine processEngine;
  
  @Bean
  public SimulatorPlugin simulatorPlugin() {
        return new SimulatorPlugin();
  }
  
  @Bean
  public PayloadGenerator generator() {
        return new ContentGenerator();
  }

}
