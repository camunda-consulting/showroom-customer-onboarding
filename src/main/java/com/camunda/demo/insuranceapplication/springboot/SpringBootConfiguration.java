package com.camunda.demo.insuranceapplication.springboot;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.camunda.demo.insuranceapplication.facade.ApplicationOnlineFacade;
import com.camunda.demo.insuranceapplication.facade.RestApplication;

@Configuration
public class SpringBootConfiguration {

  protected final Logger logger = LoggerFactory.getLogger(this.getClass());

  /**
   * http://stackoverflow.com/questions/35208439/use-different-paths-for-public-and-private-resources-jersey-spring-boot
   * https://dzone.com/articles/using-jax-rs-with-spring-boot-instead-of-mvc 
   */
  @Bean
  public ServletRegistrationBean publicJersey() {
    ServletRegistrationBean publicJersey = new ServletRegistrationBean(new ServletContainer( //
        new ResourceConfig().register(ApplicationOnlineFacade.class)));
    publicJersey.addUrlMappings("/camunda-showcase-insurance-application/api/*");
    publicJersey.setName("ApplicationRestApi");
    publicJersey.setLoadOnStartup(0);

    logger.info("Registered application REST api to path " + publicJersey.getUrlMappings());

    return publicJersey;
  }

}
