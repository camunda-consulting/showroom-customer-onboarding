package com.camunda.demo.customeronboarding.adapter;


import java.util.Map;

import org.springframework.stereotype.Component;

import com.camunda.demo.customeronboarding.ProcessConstants;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;

@Component
public class CreateReferenceIdAdapter {

  @ZeebeWorker(type = "createReferenceIdAdapter", autoComplete = true)
  public Map<String, String> createReferenceIdAdapter(final JobClient client, final ActivatedJob job) {

    return Map.of(ProcessConstants.VAR_NAME_documentReferenceId, String.valueOf(System.currentTimeMillis()));
  }
}
