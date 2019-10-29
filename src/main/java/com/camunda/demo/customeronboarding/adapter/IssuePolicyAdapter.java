package com.camunda.demo.customeronboarding.adapter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.springframework.stereotype.Component;

import com.camunda.demo.customeronboarding.ProcessConstants;
import com.camunda.demo.customeronboarding.model.NewApplication;

@Component
public class IssuePolicyAdapter implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    ObjectValue applicationVariable = execution.getVariableTyped(ProcessConstants.VAR_NAME_application);

    ((NewApplication) applicationVariable.getValue()).setContractNumber(String.valueOf(System.currentTimeMillis()));

    execution.setVariable(ProcessConstants.VAR_NAME_application, applicationVariable);
  }

}
