package com.camunda.demo.customeronboarding;

import static com.camunda.demo.customeronboarding.ProcessConstants.FILEPATH_CUSTOMER_ONBOARDING_DE;
import static com.camunda.demo.customeronboarding.ProcessConstants.FILEPATH_CUSTOMER_ONBOARDING_DE_OLD;
import static com.camunda.demo.customeronboarding.ProcessConstants.FILEPATH_CUSTOMER_ONBOARDING_EN;
import static com.camunda.demo.customeronboarding.ProcessConstants.FILEPATH_CUSTOMER_ONBOARDING_EN_OLD;
import static com.camunda.demo.customeronboarding.ProcessConstants.FILEPATH_DOCUMENT_REQUEST_DE;
import static com.camunda.demo.customeronboarding.ProcessConstants.FILEPATH_DOCUMENT_REQUEST_EN;
import static com.camunda.demo.customeronboarding.ProcessConstants.FILEPATH_RISK_CHECK_DE;
import static com.camunda.demo.customeronboarding.ProcessConstants.FILEPATH_RISK_CHECK_EN;

import org.camunda.bpm.application.AbstractProcessApplication;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.DeploymentBuilder;
import org.camunda.bpm.engine.repository.ResumePreviousBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeploymentService {

  private ProcessEngine processEngine;
  private AbstractProcessApplication processApp;

  @Autowired
  public DeploymentService(ProcessEngine processEngine, AbstractProcessApplication processApp) {
    this.processEngine = processEngine;
    this.processApp = processApp;
  }

  private void deployProcessDiagramms(String... classpathresourceNames) {
    DeploymentBuilder deploymentBuilder = processEngine.getRepositoryService().createDeployment(processApp.getReference()) //
        // .enableDuplicateFiltering(false) //
        .name(ProcessConstants.DEPLOYMENT_NAME)
        .resumePreviousVersions() //
        .resumePreviousVersionsBy(ResumePreviousBy.RESUME_BY_PROCESS_DEFINITION_KEY); //

    for (String resource : classpathresourceNames) {
      deploymentBuilder = deploymentBuilder.addClasspathResource(resource);
    }
    Deployment deployment = deploymentBuilder.deploy();
     
    processEngine.getManagementService().registerProcessApplication(deployment.getId(), processApp.getReference());
  }

  public void deployStandardProcesses() {
    deployProcessDiagramms(FILEPATH_RISK_CHECK_EN, FILEPATH_RISK_CHECK_DE, FILEPATH_DOCUMENT_REQUEST_EN,
        FILEPATH_DOCUMENT_REQUEST_DE, FILEPATH_CUSTOMER_ONBOARDING_DE_OLD, FILEPATH_CUSTOMER_ONBOARDING_EN_OLD);
  }

  public void deployCustomerOnboardCurrent() {
    deployProcessDiagramms(FILEPATH_CUSTOMER_ONBOARDING_DE, FILEPATH_CUSTOMER_ONBOARDING_EN);
  }
  
  public void deployAllCurrent() {
    deployProcessDiagramms(FILEPATH_RISK_CHECK_EN, FILEPATH_RISK_CHECK_DE, FILEPATH_DOCUMENT_REQUEST_EN,
        FILEPATH_DOCUMENT_REQUEST_DE, FILEPATH_CUSTOMER_ONBOARDING_DE, FILEPATH_CUSTOMER_ONBOARDING_EN);
  }
}
