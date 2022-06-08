package com.camunda.demo.customeronboarding.adapter;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.camunda.demo.customeronboarding.model.NewApplication;
import com.camunda.demo.customeronboarding.service.ScoringService;

import io.camunda.zeebe.spring.client.annotation.ZeebeVariablesAsType;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;

@Component
public class CalculateScore {
    
    @Autowired
    private ScoringService scoringService;
    
	@ZeebeWorker(type = "calculateScore", autoComplete = true)
	public NewApplication calculateScore(@ZeebeVariablesAsType NewApplication newApplication) {

		newApplication.setScore(scoringService.getScore(newApplication));
		return newApplication;
	}
}
