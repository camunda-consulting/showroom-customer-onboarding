package com.camunda.demo.insuranceapplication.adapter;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.camunda.demo.insuranceapplication.ProcessConstants;
import com.camunda.demo.insuranceapplication.model.Application;

@Named
public class IssuePolicyAdapter implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    Application antrag = (Application) execution.getVariable(ProcessConstants.VAR_NAME_application);    
    // do data transformation
    // call real service 
   
    
    antrag.setFeeInCent(21300);
    antrag.setContractNumber(String.valueOf(System.currentTimeMillis()));
    
    execution.setVariable(ProcessConstants.VAR_NAME_application, antrag);
  }

}
