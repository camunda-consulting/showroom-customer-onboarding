package com.camunda.demo.insuranceapplication.springboot;

import org.camunda.bpm.spring.boot.starter.webapp.CamundaBpmWebappAutoConfiguration;
import org.camunda.bpm.spring.boot.starter.webapp.CamundaBpmWebappInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
public class CamundanziaMvcConfig extends CamundaBpmWebappAutoConfiguration {

  @Bean
  public CamundaBpmWebappInitializer camundaBpmWebappInitializer() {
    return new CamundaBpmWebappInitializer();
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // overwrite handlers from CamundaBpmWebappAutoConfiguration as we want to
    // have the standard URL
    // pattern in sync with TOmcat/Wildfly distributions
    registry.addResourceHandler("/lib/**").addResourceLocations("classpath:/lib/");
    registry.addResourceHandler("/api/**").addResourceLocations("classpath:/api/");
    registry.addResourceHandler("/app/**").addResourceLocations("classpath:/app/");

    registry.addResourceHandler("/forms/**").addResourceLocations("classpath:/forms/");

    registry.addResourceHandler("/camunda-showcase-insurance-application/online/**").addResourceLocations( "classpath:/online/").setCachePeriod(0);
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/camunda-showcase-insurance-application/").setViewName("forward:/camunda-showcase-insurance-application/online/index.html");
    registry.addViewController("/camunda-showcase-insurance-application/online/")
        .setViewName("forward:/camunda-showcase-insurance-application/online/index.html");
    registry.addRedirectViewController("/", "/app/");
  }
}