package com.camunda.demo.customeronboarding.adapter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.camunda.demo.customeronboarding.ProcessConstants;

public class CreateReferenceIdAdapter implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    execution.setVariable(ProcessConstants.VAR_NAME_documentReferenceId, String.valueOf(System.currentTimeMillis()));
  }

}
