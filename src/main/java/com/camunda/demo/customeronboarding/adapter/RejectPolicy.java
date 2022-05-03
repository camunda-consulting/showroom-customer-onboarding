package com.camunda.demo.customeronboarding.adapter;

import com.camunda.demo.customeronboarding.model.NewApplication;
import io.camunda.zeebe.spring.client.annotation.ZeebeVariablesAsType;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RejectPolicy {
  private static final Logger LOGGER = LoggerFactory.getLogger(RejectPolicy.class);


  @ZeebeWorker(type = "rejectPolicy", autoComplete = true)
  public NewApplication issuePolicy(@ZeebeVariablesAsType NewApplication newApplication) {

    LOGGER.info("Policy for %s rejected!", newApplication.getApplicant().getName());

    return newApplication;
  }

}
