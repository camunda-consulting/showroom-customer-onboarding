package com.camunda.demo.insuranceapplication.adapter;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.value.ObjectValue;

import com.camunda.demo.insuranceapplication.ProcessConstants;
import com.camunda.demo.insuranceapplication.model.NewApplication;

@Named
public class IssuePolicyAdapter implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    ObjectValue applicationVariable = execution.getVariableTyped(ProcessConstants.VAR_NAME_application);

    ((NewApplication) applicationVariable.getValue()).setContractNumber(String.valueOf(System.currentTimeMillis()));

    execution.setVariable(ProcessConstants.VAR_NAME_application, applicationVariable);
  }

}
