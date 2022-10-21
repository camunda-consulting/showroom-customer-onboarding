package com.camunda.demo.customeronboarding.adapter;

import java.time.ZoneId;

import org.springframework.stereotype.Component;

import com.camunda.demo.customeronboarding.model.NewApplication;

import io.camunda.zeebe.spring.client.annotation.VariablesAsType;
import io.camunda.zeebe.spring.client.annotation.JobWorker;

@Component
public class CalculateScore {

	@JobWorker(type = "calculateScore", autoComplete = true)
	public NewApplication calculateScore(@VariablesAsType NewApplication newApplication) {

		int yearLastDigit = newApplication.getApplicant().getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear() % 10;

		int score = 97;

		if(yearLastDigit == 3) {
			score = 93;
		} else if (yearLastDigit == 5) {
			score = 82;
		} 
		newApplication.setScore(score);
		return newApplication;
	}
}
