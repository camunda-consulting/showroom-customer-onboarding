package com.camunda.demo.customeronboarding;

import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.camunda.consulting.simulator.PayloadGenerator;
import com.camunda.consulting.simulator.SimulatorPlugin;
import com.camunda.demo.customeronboarding.DemoData.ContentGenerator;
import com.camunda.demo.customeronboarding.model.NewApplication;
import com.camunda.demo.sso.AutoLoginAuthenticationFilter;

@Configuration
public class StartConfiguration {

  private final static Logger LOGGER = LoggerFactory.getLogger(StartConfiguration.class);
  
  @Bean(name="corporationMapper")
  public ExecutionListener getMapper () {
    return (execution) -> {
      NewApplication application = (NewApplication) execution.getVariable("application");
      execution.setVariable("corporation", application.getCorporation());
    };
  }
  
  @Bean
  @ConditionalOnProperty(
	  value="shouldsimulate",
	  havingValue = "true",
	  matchIfMissing = false)
  public SimulatorPlugin simulatorPlugin() {
	  LOGGER.info("------LOAD SIMULATOR PLUGIN------");
        return new SimulatorPlugin();
  }

  @Bean
  @ConditionalOnProperty(
	  value="shouldsimulate",
	  havingValue = "true",
	  matchIfMissing = false)
  public PayloadGenerator generator() {
	  LOGGER.info("------LOAD PAYLOAD GENERATOR------");
        return new ContentGenerator();
  }

  @Bean
  public FilterRegistrationBean<AutoLoginAuthenticationFilter> loggingFilter(){
      FilterRegistrationBean<AutoLoginAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
           
      registrationBean.setFilter(new AutoLoginAuthenticationFilter());
      registrationBean.addUrlPatterns("/*");
           
      return registrationBean;    
  }

}
