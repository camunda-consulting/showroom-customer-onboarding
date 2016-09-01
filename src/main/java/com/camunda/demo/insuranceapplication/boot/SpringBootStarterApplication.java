package com.camunda.demo.insuranceapplication.boot;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.spring.boot.starter.SpringBootProcessApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.camunda.demo.environment.LicenseHelper;
import com.camunda.demo.environment.UserDataGenerator;

@SpringBootApplication
public class SpringBootStarterApplication extends SpringBootProcessApplication {

	@Autowired
	private ProcessEngine processEngine;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStarterApplication.class, args);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		super.afterPropertiesSet();
//		ProcessApplicationDemoSetup.executeDefaultSetup(processEngine, InsuranceProcessApplication.PROCESS_DEFINITION_KEY, getReference());
		LicenseHelper.setLicense(processEngine);
		UserDataGenerator.createDefaultUsers(processEngine);
	}

}
