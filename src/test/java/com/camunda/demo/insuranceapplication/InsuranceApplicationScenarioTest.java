package com.camunda.demo.insuranceapplication;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareAssertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;
import org.camunda.bpm.scenario.ProcessScenario;
import org.camunda.bpm.scenario.Scenario;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.camunda.demo.insuranceapplication.adapter.SendEmailService;
import com.camunda.demo.insuranceapplication.model.Application;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = { "com.camunda.demo.insuranceapplication.adapter.*" })
@Deployment(resources = { "InsuranceApplication.bpmn", "DocumentRequest.bpmn", "RiskAssessment.dmn" })
public class InsuranceApplicationScenarioTest {

	@Rule
	public ProcessEngineRule rule = new ProcessEngineRule();
	
	// Mock all waitstates in main process and call activity with a scenario
	@Mock
	private ProcessScenario insuranceApplication;
	@Mock
	private ProcessScenario documentRequest;
	@Mock
	private SendEmailService sendEmailService;
	
	
	@Before
	  public void setup() throws Exception {
	    MockitoAnnotations.initMocks(this);
	    PowerMockito.whenNew(SendEmailService.class).withAnyArguments().thenReturn(sendEmailService);
	  }
	
	@Test
	  public void testNoRiskShadowProcessing() throws Exception {
	    Application application = DemoData.createNewApplication(30, "VW", "Golf V");
	    VariableMap variables = Variables.createVariables().putValue( //
	        ProcessConstants.VAR_NAME_application, //
	        Variables.objectValue(application).serializationDataFormat(SerializationDataFormats.JSON).create());

	    Scenario scenario = Scenario.run(insuranceApplication) //
	        .startByKey(ProcessConstants.PROCESS_KEY_insurance_application, variables).execute();

	    assertThat(scenario.instance(insuranceApplication)).variables().containsEntry(ProcessConstants.VAR_NAME_riskAssessment, "green");
	    verify(insuranceApplication, never()).hasStarted("CallActivityDecideAboutApplication");
	    verify(insuranceApplication).hasFinished("EndEventApplicationIssued");
	    
	    verify(sendEmailService, times(1)).sendEmail(eq(application.getApplicant().getEmail()), anyString(), anyString());
	    
	  }
	
	@Test
	  public void testRedRiskShadowProcessing() throws Exception {
	    Application application = DemoData.createNewApplication(20, "Porsche", "911");
	    VariableMap variables = Variables.createVariables().putValue( //
	        ProcessConstants.VAR_NAME_application, //
	        Variables.objectValue(application).serializationDataFormat(SerializationDataFormats.JSON).create());

	    Scenario scenario = Scenario.run(insuranceApplication) //
	        .startByKey(ProcessConstants.PROCESS_KEY_insurance_application, variables).execute();

	    assertThat(scenario.instance(insuranceApplication)).variables().containsEntry(ProcessConstants.VAR_NAME_riskAssessment, "red");
	    verify(insuranceApplication, never()).hasStarted("CallActivityDecideAboutApplication");
	    verify(insuranceApplication).hasFinished("EndEventApplicationRejected");
	    
	    verify(sendEmailService, times(1)).sendEmail(eq(application.getApplicant().getEmail()), anyString(), anyString());

	  }
}
