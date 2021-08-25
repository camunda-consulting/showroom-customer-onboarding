package com.camunda.demo.customeronboarding.adapter;

import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;


@Component
@EnableZeebeClient
public class LoggerDelegate { 

  @ZeebeWorker(type="logger")
  public void loggerDelegate(final JobClient client, final ActivatedJob job) {
    
    final Logger LOGGER = Logger.getLogger(LoggerDelegate.class.getName());

    LOGGER.info("\n\n  ... Logger invoked by "
    + "process=" + job.getBpmnProcessId()
    + " v" + job.getProcessDefinitionVersion()
    + ", elementId=" + job.getElementId()
    + ", jobType=" + job.getType()
    + ", headers=" + job.getCustomHeaders()
    + ", worker=" + job.getWorker()
    + ", retries=" + job.getRetries()
    + ", deadline=" + job.getDeadline()
    + ", variables=" + job.getVariablesAsMap()
    + ", jobKey=" + job.getKey()
    + ", elementInstanceKey=" + job.getElementInstanceKey()
    + ", processInstanceKey=" + job.getProcessInstanceKey()
    + ", processDefinitionKey=" + job.getProcessDefinitionKey()
    + " \n\n");

    client
      .newCompleteCommand(job.getKey())
		  .send()
		  .join();
  }

}


