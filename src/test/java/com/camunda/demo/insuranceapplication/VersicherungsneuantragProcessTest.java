package com.camunda.demo.insuranceapplication;

import static com.camunda.demo.insuranceapplication.DemoData.createGreenInitVars;
import static com.camunda.demo.insuranceapplication.DemoData.createRedInitVars;
import static com.camunda.demo.insuranceapplication.DemoData.createYellowInitVars;
import static org.assertj.core.api.Assertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.bpmn.AbstractAssertions.init;
import static org.camunda.bpm.engine.test.assertions.bpmn.AbstractAssertions.processEngine;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareAssertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.calledProcessInstance;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.complete;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.execute;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.job;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.runtimeService;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.task;
import static org.camunda.bpm.engine.test.assertions.cmmn.CmmnAwareTests.withVariables;
import static org.camunda.spin.Spin.JSON;
import static org.mockito.Matchers.anyString;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareOnlyThisForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.camunda.demo.insuranceapplication.DemoData;
import com.camunda.demo.insuranceapplication.ProcessConstants;
import com.camunda.demo.insuranceapplication.adapter.SendEmailService;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */

/*
 * we must use this PowerMock-restriction together with PowerMockRule instead of
 * PowerMockRunner until https://github.com/powermock/powermock/issues/687 is
 * fixed
 */
@PowerMockIgnore("*")
@PrepareOnlyThisForTest(fullyQualifiedNames = { "com.camunda.demo.insuranceapplication.adapter.EmailAdapter" })
@Deployment(resources = { "insurance_application_en.bpmn", "document_request_en.bpmn", "risk_check_en.dmn", "insurance_application_de.bpmn",
    "document_request_de.bpmn", "risk_check_de.dmn" })
public class VersicherungsneuantragProcessTest {
  static final Logger logger = LoggerFactory.getLogger(VersicherungsneuantragProcessTest.class);

  @Rule
  public PowerMockRule powermock = new PowerMockRule();

  @ClassRule
  @Rule
  public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

  static {
    // enable more detailed logging
    // LogUtil.readJavaUtilLoggingConfigFromClasspath(); // process engine
    // LogFactory.useJdkLogging(); // MyBatis
  }

  @Before
  public void setup() throws Exception {
    init(rule.getProcessEngine());
    MockitoAnnotations.initMocks(this);
    PowerMockito.whenNew(SendEmailService.class).withAnyArguments().thenReturn(sendEmailService);
    Mockito.doAnswer(invocation -> {
      logger.info("I'm mocking a mail to <" + invocation.getArguments()[0] + ">.");
      logger.debug("  Mock mail subject: " + invocation.getArguments()[2]);
      logger.debug("  Mock mail body: " + invocation.getArguments()[1]);
      return null;
    }).when(sendEmailService).sendEmail(anyString(), anyString(), anyString());
  }

  /**
   * Just tests if the process definition is deployable.
   */
  @Test
  public void testParsingAndDeployment() {
    // nothing is done here, as we just want to check for exceptions during
    // deployment
    processEngine();
  }

  @Mock
  private SendEmailService sendEmailService;

  @Test
  public void testAutomaticIssued() {
    testAutomaticIssued(ProcessConstants.PROCESS_KEY_insurance_application_en);
    testAutomaticIssued(ProcessConstants.PROCESS_KEY_insurance_application_de);
  }

  protected void testAutomaticIssued(String processKey) {
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(processKey, createGreenInitVars());

    assertThat(processInstance).job();
    execute(job()); // start event
    execute(job()); // send task

    assertThat(processInstance) //
        .isEnded() //
        .hasPassedInOrder("BusinessRuleTask_CheckApplicationAutomatically", "ServiceTask_DeliverPolicy", "SendTask_SendPolicy", "EndEvent_ApplicationIssued");
  }

  @Test
  public void testAutomaticRejected() {
    testAutomaticRejected(ProcessConstants.PROCESS_KEY_insurance_application_en);
    testAutomaticRejected(ProcessConstants.PROCESS_KEY_insurance_application_de);

  }

  protected void testAutomaticRejected(String whatever) {
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(whatever, createRedInitVars());

    assertThat(processInstance).job();
    execute(job()); // start event
    execute(job()); // send task

    assertThat(processInstance) //
        .isEnded() //
        .hasPassedInOrder("BusinessRuleTask_CheckApplicationAutomatically", "ServiceTask_RejectPolicy", "SendTask_SendRejection",
            "EndEvent_ApplicationRejected");
  }

  @Test
  public void testManualImmediateApprove() {
    testManualImmediateApprove(ProcessConstants.PROCESS_KEY_insurance_application_en);
    testManualImmediateApprove(ProcessConstants.PROCESS_KEY_insurance_application_de);
  }

  protected void testManualImmediateApprove(String whatever) {
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(whatever, createYellowInitVars());

    assertThat(processInstance).job();
    execute(job()); // start event

    complete(task(), withVariables("approved", Boolean.TRUE));
    assertThat(processInstance).job(); // async after user task
    execute(job());

    assertThat(processInstance).job();
    execute(job());

    assertThat(processInstance) //
        .isEnded() //
        .hasPassed("ServiceTask_DeliverPolicy", "SendTask_SendPolicy", "EndEvent_ApplicationIssued");
  }

  @Test
  public void testManualRequestDocumentThenApprove() {
    testManualRequestDocumentThenApprove(ProcessConstants.PROCESS_KEY_insurance_application_en);
    testManualRequestDocumentThenApprove(ProcessConstants.PROCESS_KEY_insurance_application_de);
  }

  protected void testManualRequestDocumentThenApprove(String whatever) {
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(whatever, createYellowInitVars());

    assertThat(processInstance).job();
    execute(job()); // start event

    assertThat(processInstance).isWaitingAt("UserTask_DecideOnApplication");
    assertThat(processInstance).hasVariables(ProcessConstants.VAR_NAME_documentReferenceId);
    String reference = runtimeService().getVariable(processInstance.getId(), ProcessConstants.VAR_NAME_documentReferenceId).toString();

    runtimeService().correlateMessage(ProcessConstants.MESSAGE_documentRequested, new HashMap<>(),
        withVariables(ProcessConstants.VAR_NAME_requestedDocumentDescription, "Driver's license"));

    ProcessInstance documentRequest = calledProcessInstance();
    assertThat(processInstance).hasVariables(ProcessConstants.VAR_NAME_application);
    assertThat(documentRequest).hasVariables(ProcessConstants.VAR_NAME_application);

    assertThat(documentRequest).isWaitingAt("SendTask_RequestDocument");

    execute(job(documentRequest)); // send task in document request
    assertThat(documentRequest).isWaitingAt("ReceiveTask_WaitForDocument");

    // send in some driver's license
    runtimeService().correlateMessage(ProcessConstants.MESSAGE_documentReceived, new HashMap<String, Object>(),
        withVariables(ProcessConstants.VAR_NAME_document, DemoData.createDocument()));

    // this should finalize sub-process
    assertThat(documentRequest) //
        .isEnded() //
        .hasPassedInOrder("SendTask_RequestDocument", "ReceiveTask_WaitForDocument", "EndEvent_GotDocument");

    // check that document made it to the list
    assertThat(processInstance).hasVariables(ProcessConstants.VAR_NAME_documents);
    @SuppressWarnings("unchecked")
    Map<String, String> documents = (Map<String, String>) JSON(runtimeService().getVariable(processInstance.getId(), ProcessConstants.VAR_NAME_documents))
        .mapTo(HashMap.class);
    assertThat(documents).hasSize(1).containsKey(reference);

    complete(task(), withVariables("approved", Boolean.TRUE)); // accept
                                                               // application
    assertThat(processInstance).job(); // async after user task
    execute(job());

    assertThat(processInstance).job(); // send task
    execute(job());

    assertThat(processInstance) //
        .isEnded() //
        .hasPassedInOrder("ServiceTask_DeliverPolicy", "SendTask_SendPolicy", "EndEvent_ApplicationIssued");
  }
}
