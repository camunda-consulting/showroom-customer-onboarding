package com.camunda.demo.insuranceapplication.adapter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.camunda.demo.insuranceapplication.ProcessConstants;

public class CreateReferenceIdAdapter implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    execution.setVariable(ProcessConstants.VAR_NAME_refernceId, String.valueOf(System.currentTimeMillis()));
  }

}
