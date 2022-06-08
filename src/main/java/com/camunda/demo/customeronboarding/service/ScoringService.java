package com.camunda.demo.customeronboarding.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.camunda.demo.customeronboarding.model.NewApplication;

import io.camunda.zeebe.spring.client.annotation.ZeebeVariablesAsType;

@Service
public class ScoringService {


    @Value("${scoring.url}")
    private String scoringUrl;

    @Autowired
    private RestTemplate restTemplate;
    
    public Integer getScore(@ZeebeVariablesAsType NewApplication newApplication) {

        HttpEntity<NewApplication> request = new HttpEntity<NewApplication>(newApplication);
        ResponseEntity<Map<String,Integer>> result = restTemplate.exchange(scoringUrl, HttpMethod.POST, request,new ParameterizedTypeReference<Map<String, Integer>>() {});

        return result.getBody().get("score");
    }
}
