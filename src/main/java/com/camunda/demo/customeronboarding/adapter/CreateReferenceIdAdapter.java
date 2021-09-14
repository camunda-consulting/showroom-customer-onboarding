package com.camunda.demo.customeronboarding.adapter;


import java.util.Map;

import com.camunda.demo.customeronboarding.ProcessConstants;

import org.springframework.stereotype.Component;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;

@Component
@EnableZeebeClient
public class CreateReferenceIdAdapter {

  @ZeebeWorker(type = "createReferenceIdAdapter")
  public void createReferenceIdAdapter(final JobClient client, final ActivatedJob job) {

    client.newCompleteCommand(job.getKey())
    .variables(Map.of(ProcessConstants.VAR_NAME_documentReferenceId, String.valueOf(System.currentTimeMillis())))
    .send()
    .join();
  }
}
