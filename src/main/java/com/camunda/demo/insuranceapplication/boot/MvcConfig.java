package com.camunda.demo.insuranceapplication.boot;

import org.camunda.bpm.spring.boot.starter.webapp.CamundaBpmWebappAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter  {
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){   	
    	// overwrite handlers from CamundaBpmWebappAutoConfiguration as we want to have the standard URL 
    	// pattern in sync with TOmcat/Wildfly distributions
//        registry.addResourceHandler("/camunda/lib/**").addResourceLocations("classpath:/lib/");
//        registry.addResourceHandler("/camunda/api/**").addResourceLocations("classpath:/api/");
//        registry.addResourceHandler("/camunda/app/**").addResourceLocations("classpath:/app/");   
    
    	registry.addResourceHandler("/camunda-showcase-insurance-application/online/**")
	            .addResourceLocations("/online/")
	            .setCachePeriod(0);
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/camunda-showcase-insurance-application/online/").setViewName("forward:/camunda-showcase-insurance-application/online/index.html");
    }
}