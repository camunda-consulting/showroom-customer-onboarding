package com.camunda.demo.insuranceapplication.springboot;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.spring.boot.starter.SpringBootProcessApplication;
import org.camunda.bpm.spring.boot.starter.webapp.CamundaBpmWebappAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.camunda.consulting.util.LicenseHelper;
import com.camunda.consulting.util.UserGenerator;
import com.camunda.demo.insuranceapplication.InsuranceApplicationProcessApplication;

@SpringBootApplication
@EnableAutoConfiguration(exclude={CamundaBpmWebappAutoConfiguration.class})
public class SpringBootStarterApplication extends SpringBootProcessApplication {

	@Autowired
	private ProcessEngine processEngine;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStarterApplication.class, args);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		super.afterPropertiesSet();
//		InsuranceApplicationProcessApplication.generateDemoData(BpmPlatform.getDefaultProcessEngine(), getReference());
    LicenseHelper.setLicense(processEngine);
    UserGenerator.createDefaultUsers(processEngine);
    InsuranceApplicationProcessApplication.setupUsersForDemo(processEngine);
	}
	
}
