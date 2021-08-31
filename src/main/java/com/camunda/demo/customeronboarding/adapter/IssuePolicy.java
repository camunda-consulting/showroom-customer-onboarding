package com.camunda.demo.customeronboarding.adapter;

import org.springframework.stereotype.Component;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;

import java.util.Map;

import com.camunda.demo.customeronboarding.ProcessConstants;
import com.camunda.demo.customeronboarding.model.NewApplication;
import com.camunda.demo.customeronboarding.model.NewApplicationVariable;

@Component
@EnableZeebeClient
public class IssuePolicy {

  @ZeebeWorker(type = "issuePolicy")
  public void issuePolicy(final JobClient client, final ActivatedJob job) {

    NewApplication newApplication = job.getVariablesAsType(NewApplicationVariable.class).getApplication();
    newApplication.setContractNumber(String.valueOf(System.currentTimeMillis()));

    client.newCompleteCommand(job.getKey())
      .variables(Map.of(ProcessConstants.VAR_NAME_application, newApplication))
      .send()
      .join();
  }

}
