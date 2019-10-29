package com.camunda.demo.customeronboarding;

import static com.camunda.demo.customeronboarding.DemoData.createGreenInitVars;
import static com.camunda.demo.customeronboarding.DemoData.createRedInitVars;
import static com.camunda.demo.customeronboarding.DemoData.createYellowInitVars;
import static org.camunda.bpm.engine.test.assertions.bpmn.AbstractAssertions.processEngine;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.calledProcessInstance;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.complete;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.execute;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.externalTask;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.job;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.runtimeService;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.task;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.withVariables;
import static org.camunda.spin.Spin.JSON;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class VersicherungsneuantragProcessTest extends SpringBootProcessTest {
  
  static final Logger logger = LoggerFactory.getLogger(VersicherungsneuantragProcessTest.class);
  
  /**
   * Just tests if the process definition is deployable.
   */
  @Test
  public void testParsingAndDeployment() {
    // nothing is done here, as we just want to check for exceptions during
    // deployment
    processEngine();
  }

  @Test
  public void testAutomaticIssued() {
    testAutomaticIssued(ProcessConstants.PROCESS_KEY_customer_onboarding_en);
    testAutomaticIssued(ProcessConstants.PROCESS_KEY_customer_onboarding_de);
  }

  protected void testAutomaticIssued(String processKey) {
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(processKey, createGreenInitVars(isGerman(processKey)));

    assertThat(processInstance).job();
    execute(job()); // start event
    execute(job()); // transaction border before external task
    complete(externalTask());// send task

    assertThat(processInstance) //
        .isEnded() //
        .hasPassedInOrder("BusinessRuleTask_CheckApplicationAutomatically", "ServiceTask_DeliverPolicy", "SendTask_SendPolicy", "EndEvent_ApplicationIssued");
  }

  @Test
  public void testAutomaticRejected() {
    testAutomaticRejected(ProcessConstants.PROCESS_KEY_customer_onboarding_en);
    testAutomaticRejected(ProcessConstants.PROCESS_KEY_customer_onboarding_de);

  }

  protected void testAutomaticRejected(String processKey) {
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(processKey, createRedInitVars(isGerman(processKey)));

    assertThat(processInstance).job();
    execute(job()); // start event
    execute(job()); // transaction border before external task
    complete(externalTask());// send task

    assertThat(processInstance) //
        .isEnded() //
        .hasPassedInOrder("BusinessRuleTask_CheckApplicationAutomatically", "ServiceTask_RejectPolicy", "SendTask_SendRejection",
            "EndEvent_ApplicationRejected");
  }

  @Test
  public void testManualImmediateApprove() {
    testManualImmediateApprove(ProcessConstants.PROCESS_KEY_customer_onboarding_en);
    testManualImmediateApprove(ProcessConstants.PROCESS_KEY_customer_onboarding_de);
  }

  protected void testManualImmediateApprove(String processKey) {
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(processKey, createYellowInitVars(isGerman(processKey)));

    assertThat(processInstance).job();
    execute(job()); // start event

    complete(task(), withVariables("approved", Boolean.TRUE));
    assertThat(processInstance).job(); // async after user task
    execute(job());

    assertThat(processInstance).job();
    execute(job()); // transaction border before external task
    complete(externalTask());// send task

    assertThat(processInstance) //
        .isEnded() //
        .hasPassed("ServiceTask_DeliverPolicy", "SendTask_SendPolicy", "EndEvent_ApplicationIssued");
  }

  @Test
  public void testManualRequestDocumentThenApprove() {
    testManualRequestDocumentThenApprove(ProcessConstants.PROCESS_KEY_customer_onboarding_en);
    testManualRequestDocumentThenApprove(ProcessConstants.PROCESS_KEY_customer_onboarding_de);
  }

  protected void testManualRequestDocumentThenApprove(String processKey) {
    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey(processKey, createYellowInitVars(isGerman(processKey)));

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
    execute(job()); // transaction border before external task
    complete(externalTask());// send task in document request
    assertThat(documentRequest).isWaitingAt("ReceiveTask_WaitForDocument");

    // send in some driver's license
    runtimeService().correlateMessage(ProcessConstants.MESSAGE_documentReceived, new HashMap<String, Object>(),
        withVariables(ProcessConstants.VAR_NAME_document, DemoData.createDocument()));
    execute(job());

    // this should finalize sub-process
    assertThat(documentRequest) //
        .isEnded() //
        .hasPassedInOrder("SendTask_RequestDocument", "ReceiveTask_WaitForDocument", "EndEvent_GotDocument");

    // check that document made it to the list
    assertThat(processInstance).hasVariables(ProcessConstants.VAR_NAME_documents);
    @SuppressWarnings("unchecked")
    Map<String, String> documents = (Map<String, String>) JSON(runtimeService().getVariable(processInstance.getId(), ProcessConstants.VAR_NAME_documents))
        .mapTo(HashMap.class);
    org.assertj.core.api.Assertions.assertThat(documents).hasSize(1).containsKey(reference);

    complete(task(), withVariables("approved", Boolean.TRUE)); // accept
                                                               // application
    assertThat(processInstance).job(); // async after user task
    execute(job());

    assertThat(processInstance).job(); // send task
    execute(job()); // transaction border before external task
    complete(externalTask());// send task

    assertThat(processInstance) //
        .isEnded() //
        .hasPassedInOrder("ServiceTask_DeliverPolicy", "SendTask_SendPolicy", "EndEvent_ApplicationIssued");
  }
}
