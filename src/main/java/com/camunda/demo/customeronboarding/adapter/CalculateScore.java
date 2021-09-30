package com.camunda.demo.customeronboarding.adapter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import com.camunda.demo.customeronboarding.model.NewApplication;

@Component
public class CalculateScore implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		NewApplication application = (NewApplication) execution.getVariable("application");

		execution.setVariable("score", application.getApplicant().getScore());
	}

}
