package com.camunda.demo.insuranceapplication;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.UUID;

import org.apache.commons.mail.EmailException;
import org.camunda.bpm.engine.runtime.CaseExecution;
import org.camunda.bpm.engine.runtime.CaseInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;
import org.camunda.bpm.engine.variable.value.SerializationDataFormat;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;



import com.camunda.demo.insuranceapplication.ProcessConstants;
import com.camunda.demo.insuranceapplication.adapter.SendEmailService;
import com.camunda.demo.insuranceapplication.model.Application;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = {"com.camunda.demo.insuranceapplication.adapter.*"})
public class InsuranceApplicationProcessTest {

  @Rule
  public ProcessEngineRule rule = new ProcessEngineRule();

  // enable more detailed logging
  static {
//    LogUtil.readJavaUtilLoggingConfigFromClasspath(); // process engine
//    LogFactory.useJdkLogging(); // MyBatis
  }
  
  @Before
  public void setup() {
    init(rule.getProcessEngine());
    MockitoAnnotations.initMocks(this);
  }
  
  /**
   * Just tests if the process definition is deployable.
   */
  @Test
  @Deployment(resources = {"InsuranceApplication.bpmn", "DocumentRequest.bpmn", "RiskAssessment.dmn"})
  public void testParsingAndDeployment() {
    // nothing is done here, as we just want to check for exceptions during deployment
  }

  @Mock
  private SendEmailService sendEmailService;
  
  @Test
  @Deployment(resources = {"InsuranceApplication.bpmn", "RiskAssessment.dmn"})
  public void testShadowProcessingPolicyIssued() throws Exception {
	PowerMockito.whenNew(SendEmailService.class).withAnyArguments().thenReturn(sendEmailService);
	
	Application application = DemoData.createNewApplication(40, "VW", "Golf V");
    
    VariableMap variables = Variables.createVariables().putValue(
        ProcessConstants.VAR_NAME_application,
        Variables.objectValue(application).serializationDataFormat(SerializationDataFormats.JSON).create());
        
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(ProcessConstants.PROCESS_KEY_insurance_application, variables);
    
    assertThat(processInstance).job();
    execute(job()); // start event
    execute(job()); // send task
    
    // Shadow processing
    assertThat(processInstance)
    	.isEnded()
    	.hasPassed("BusinessRuleTaskCheckRisks", "ServiceTaskIssuePolicy", "SendTaskSendPolicy", "EndEventApplicationIssued");
    
    // verify that mail service was called correctly
    verify(sendEmailService, times(1)).sendEmail(eq(application.getApplicant().getEmail()), anyString(), anyString());
  }

  @Test
  @Deployment(resources = {"InsuranceApplication.bpmn", "RiskAssessment.dmn"})
  public void testShadowProcessingRejected() throws Exception {
	PowerMockito.whenNew(SendEmailService.class).withAnyArguments().thenReturn(sendEmailService);
	  
	Application application = DemoData.createNewApplication(20, "Porsche", "911");
	VariableMap variables = Variables.createVariables().putValue( //
	        ProcessConstants.VAR_NAME_application, //
	        Variables.objectValue(application).serializationDataFormat(SerializationDataFormats.JSON).create());
        
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(ProcessConstants.PROCESS_KEY_insurance_application, variables);
    
    assertThat(processInstance).job();
    execute(job()); // start event
    execute(job()); // send task
        
    assertThat(processInstance)
      .isEnded() //
      .hasPassed("BusinessRuleTaskCheckRisks", "SendTaskSendRejection", "EndEventApplicationRejected");
  
    // verify that mail service was called correctly
    verify(sendEmailService, times(1)).sendEmail(eq(application.getApplicant().getEmail()), anyString(), anyString());
  
  }

  @Test
  @Deployment(resources = {"InsuranceApplication.bpmn", "RiskAssessment.dmn", "DocumentRequest.bpmn", "ApplicationCheck.cmmn"})
  public void testManualProcessing() throws Exception{
	  PowerMockito.whenNew(SendEmailService.class).withAnyArguments().thenReturn(sendEmailService);
	  
	  Application application = DemoData.createNewApplication(40, "BMW", "X3");
	  VariableMap variables = Variables.createVariables().putValue( //
			  ProcessConstants.VAR_NAME_application, //
			  Variables.objectValue(application).serializationDataFormat(SerializationDataFormats.JSON).create());
	  
	  ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(ProcessConstants.PROCESS_KEY_insurance_application, variables);
	  
	  assertThat(processInstance).job();
	  execute(job());
	  
	  Task task = taskService().createTaskQuery().singleResult();
	  complete(task, withVariables("approved", Boolean.TRUE));
	  
	  assertThat(processInstance).job();
	  execute(job());
	  
	  assertThat(processInstance).isEnded().hasPassed("ServiceTaskIssuePolicy", "SendTaskSendPolicy");
	  
	  verify(sendEmailService, times(1)).sendEmail(eq(application.getApplicant().getEmail()), anyString(), anyString());  
  }
}
