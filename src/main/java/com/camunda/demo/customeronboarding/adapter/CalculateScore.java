package com.camunda.demo.customeronboarding.adapter;

import java.time.ZoneId;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.camunda.demo.customeronboarding.model.NewApplication;

public class CalculateScore implements JavaDelegate {


	@Override
	public void execute(DelegateExecution execution) throws Exception {
		NewApplication application = (NewApplication) execution.getVariable("application");
		int yearLastDigit = application.getApplicant().getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear() % 10;
		
		int score = 97;
		
		if(yearLastDigit == 3) {
			score = 93;
		} else if (yearLastDigit == 5) {
			score = 82;
		} 
		
		execution.setVariable("score", score);
	}

}
