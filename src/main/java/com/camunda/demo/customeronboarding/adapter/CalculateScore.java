package com.camunda.demo.customeronboarding.adapter;

import java.time.ZoneId;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;

import com.camunda.demo.customeronboarding.ProcessConstants;
import com.camunda.demo.customeronboarding.model.NewApplication;
import com.camunda.demo.customeronboarding.model.NewApplicationVariable;

@Component
@EnableZeebeClient
public class CalculateScore {

	@ZeebeWorker(type = "calculateScore")
	public void calculateScore(final JobClient client, final ActivatedJob job) {

		//NewApplication newApplication = (NewApplication) job.getVariablesAsMap().get(ProcessConstants.VAR_NAME_application);
		NewApplication newApplication = job.getVariablesAsType(NewApplicationVariable.class).getApplication();
		int yearLastDigit = newApplication.getApplicant().getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear() % 10;

		int score = 97;

		if(yearLastDigit == 3) {
			score = 93;
		} else if (yearLastDigit == 5) {
			score = 82;
		} 
		
		client.newCompleteCommand(job.getKey())
		.variables(Map.of(ProcessConstants.VAR_NAME_score, score))
		.send()
		.join();
	}
}
