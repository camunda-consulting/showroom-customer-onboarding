package com.camunda.demo.customeronboarding;

import static org.assertj.core.api.Assertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.historyService;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.runtimeService;
import static org.camunda.bpm.engine.test.assertions.cmmn.CmmnAwareTests.withVariables;
import static org.camunda.spin.Spin.JSON;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.mail.EmailException;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.camunda.bpm.scenario.ProcessScenario;
import org.camunda.bpm.scenario.Scenario;
import org.camunda.bpm.scenario.delegate.TaskDelegate;
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

import com.camunda.demo.customeronboarding.DemoData;
import com.camunda.demo.customeronboarding.ProcessConstants;
import com.camunda.demo.customeronboarding.adapter.SendEmailService;

/*
 * we must use this PowerMock-restriction together with PowerMockRule instead of
 * PowerMockRunner until https://github.com/powermock/powermock/issues/687 is
 * fixed
 */
@PowerMockIgnore("*")
@PrepareOnlyThisForTest(fullyQualifiedNames = { "com.camunda.demo.customeronboarding.adapter.EmailAdapter" })
@Deployment(resources = { "customer_onboarding_en.bpmn", "document_request_en.bpmn", "risk_check_en.dmn", "customer_onboarding_de.bpmn",
    "document_request_de.bpmn", "risk_check_de.dmn" })
public class VersicherungsneuantragScenarioTest {
  static final Logger logger = LoggerFactory.getLogger(VersicherungsneuantragScenarioTest.class);

  @Rule
  public PowerMockRule powermock = new PowerMockRule();

  @ClassRule
  @Rule
  public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

  // Mock all wait states in main process and call activity with a scenario
  @Mock
  private ProcessScenario customerOnboarding;
  @Mock
  private ProcessScenario documentRequest;
  @Mock
  private SendEmailService sendEmailService;

  @Before
  public void setup() throws Exception {
    MockitoAnnotations.initMocks(this);
    PowerMockito.whenNew(SendEmailService.class).withAnyArguments().thenReturn(sendEmailService);
    Mockito.doAnswer(invocation -> {
      logger.info("I'm mocking a mail to <" + invocation.getArguments()[0] + ">.");
      logger.debug("  Mock mail subject: " + invocation.getArguments()[2]);
      logger.debug("  Mock mail body: " + invocation.getArguments()[1]);
      return null;
    }).when(sendEmailService).sendEmail(anyString(), anyString(), anyString());

    // standard behavior
    when(customerOnboarding.waitsAtUserTask("UserTask_AccelerateDecision")).thenReturn(TaskDelegate::complete);
    when(customerOnboarding.runsCallActivity("CallActivity_RequestDocument")).thenReturn(Scenario.use(documentRequest));
    when(documentRequest.waitsAtUserTask("UserTask_CallCustomer")).thenReturn(TaskDelegate::complete);
  }

  @Test
  public void testAutomaticNoRisk() throws Exception {
    testAutomaticNoRisk(ProcessConstants.PROCESS_KEY_customer_onboarding_en);
  }

  @Test
  public void testAutomaticNoRisk_de() throws Exception {
    testAutomaticNoRisk(ProcessConstants.PROCESS_KEY_customer_onboarding_de);
  }

  protected void testAutomaticNoRisk(String whatever) throws EmailException {
    Scenario.run(customerOnboarding) //
        .startByKey(whatever, DemoData.createGreenInitVars()).execute();

    verify(customerOnboarding, never()).hasStarted("SubProcess_ManualCheck");
    verify(customerOnboarding, never()).hasStarted("ServiceTask_RejectPolicy");
    verify(customerOnboarding).hasCompleted("EndEvent_ApplicationIssued");

    verify(sendEmailService, times(1)).sendEmail(eq(DemoData.EMAIL), anyString(), anyString());
  }

  @Test
  public void testAutomaticHighRisk() throws Exception {
    testAutomaticHighRisk(ProcessConstants.PROCESS_KEY_customer_onboarding_en);
  }

  @Test
  public void testAutomaticHighRisk_de() throws Exception {
    testAutomaticHighRisk(ProcessConstants.PROCESS_KEY_customer_onboarding_de);
  }

  protected void testAutomaticHighRisk(String sldkfhsdf) throws EmailException {
    Scenario.run(customerOnboarding) //
        .startByKey(sldkfhsdf, DemoData.createRedInitVars()).execute();

    verify(customerOnboarding, never()).hasStarted("SubProcess_ManualCheck");
    verify(customerOnboarding, never()).hasStarted("ServiceTask_DeliverPolicy");
    verify(customerOnboarding).hasCompleted("EndEvent_ApplicationRejected");

    verify(sendEmailService, times(1)).sendEmail(eq(DemoData.EMAIL), anyString(), anyString());
  }

  @Test
  public void testManualImmediateApprove() throws Exception {
    testManualImmediateApprove(ProcessConstants.PROCESS_KEY_customer_onboarding_en);
  }

  @Test
  public void testManualImmediateApprove_de() throws Exception {
    testManualImmediateApprove(ProcessConstants.PROCESS_KEY_customer_onboarding_de);
  }

  protected void testManualImmediateApprove(String lskdfhdksf) throws EmailException {
    when(customerOnboarding.waitsAtUserTask("UserTask_DecideOnApplication")).thenReturn(task -> task.complete(withVariables("approved", true)));

    Scenario.run(customerOnboarding) //
        .startByKey(lskdfhdksf, DemoData.createYellowInitVars()).execute();

    verify(customerOnboarding, never()).hasStarted("SubProcess_ManualCheck");
    verify(customerOnboarding, never()).hasStarted("ServiceTask_RejectPolicy");
    verify(customerOnboarding).hasCompleted("EndEvent_ApplicationIssued");

    verify(sendEmailService, times(1)).sendEmail(eq(DemoData.EMAIL), anyString(), anyString());
  }

  @Test
  public void testManualDelayedApprove() throws Exception {
    testManualDelayedApprove(ProcessConstants.PROCESS_KEY_customer_onboarding_en);
  }

  @Test
  public void testManualDelayedApprove_de() throws Exception {
    testManualDelayedApprove(ProcessConstants.PROCESS_KEY_customer_onboarding_de);
  }

  protected void testManualDelayedApprove(String skdfhdsjhf) {
    when(customerOnboarding.waitsAtUserTask("UserTask_DecideOnApplication"))
        .thenReturn(task -> task.defer("P5D", () -> task.complete(withVariables("approved", true))));

    Scenario.run(customerOnboarding) //
        .startByKey(skdfhdsjhf, DemoData.createYellowInitVars()).execute();

    // we snub our employees only once (not every 2 days)
    verify(customerOnboarding, times(1)).hasCompleted("EndEvent_DecisionAccelerated");
    verify(customerOnboarding).hasCompleted("EndEvent_ApplicationIssued");
  }

  String firstRef, secondRef;

  @Test
  public void testManualRequestDocumentThenApprove() throws Exception {
    testManualRequestDocumentThenApprove(ProcessConstants.PROCESS_KEY_customer_onboarding_en);
  }

  @Test
  public void testManualRequestDocumentThenApprove_de() throws Exception {
    testManualRequestDocumentThenApprove(ProcessConstants.PROCESS_KEY_customer_onboarding_de);
  }

  protected void testManualRequestDocumentThenApprove(String sadkjfh) throws EmailException {
    when(customerOnboarding.waitsAtUserTask("UserTask_DecideOnApplication"))
        // first time send message
        .thenReturn(task -> {
          runtimeService().correlateMessage(ProcessConstants.MESSAGE_documentRequested, new HashMap<>(),
              withVariables(ProcessConstants.VAR_NAME_requestedDocumentDescription, "Driver's license"));
          firstRef = runtimeService().getVariable(task.getProcessInstanceId(), ProcessConstants.VAR_NAME_documentReferenceId).toString();
        })
        // second time approve
        .thenReturn(task -> task.complete(withVariables("approved", true)));

    // immediately send driver's license
    when(documentRequest.waitsAtReceiveTask("ReceiveTask_WaitForDocument"))
        .thenReturn(task -> runtimeService().correlateMessage(ProcessConstants.MESSAGE_documentReceived, new HashMap<String, Object>(),
            withVariables(ProcessConstants.VAR_NAME_document, DemoData.createDocument())));

    /*
     * Run and verify
     */
    ProcessInstance processInstance = Scenario.run(customerOnboarding) //
        .startByKey(sadkjfh, DemoData.createYellowInitVars()).execute().instance(customerOnboarding);

    verify(documentRequest, never()).hasStarted("UserTask_CallCustomer");
    verify(documentRequest, never()).hasStarted("SendTask_SendReminderEmail");
    verify(documentRequest).hasCompleted("EndEvent_GotDocument");
    verify(customerOnboarding, never()).hasStarted("ServiceTask_RejectPolicy");
    verify(customerOnboarding).hasCompleted("EndEvent_ApplicationIssued");

    // we expect exactly one document
    @SuppressWarnings("unchecked")
    Map<String, String> documents = (Map<String, String>) JSON(historyService().createHistoricVariableInstanceQuery().processInstanceId(processInstance.getId())
        .variableName(ProcessConstants.VAR_NAME_documents).singleResult().getValue()).mapTo(HashMap.class);
    assertThat(documents).hasSize(1).containsKey(firstRef);

    // Driver's license request and delivered policy
    verify(sendEmailService, times(2)).sendEmail(eq(DemoData.EMAIL), anyString(), anyString());
  }

  @Test
  public void testManualRequestTwoDocumentsThenApprove() throws Exception {
    testManualRequestTwoDocumentsThenApprove(ProcessConstants.PROCESS_KEY_customer_onboarding_en);
  }

  @Test
  public void testManualRequestTwoDocumentsThenApprove_de() throws Exception {
    testManualRequestTwoDocumentsThenApprove(ProcessConstants.PROCESS_KEY_customer_onboarding_de);
  }

  protected void testManualRequestTwoDocumentsThenApprove(String askdjfhdsf) throws EmailException {
    when(customerOnboarding.waitsAtUserTask("UserTask_DecideOnApplication"))
        // first time send message
        .thenReturn(task -> {
          runtimeService().correlateMessage(ProcessConstants.MESSAGE_documentRequested, new HashMap<>(),
              withVariables(ProcessConstants.VAR_NAME_requestedDocumentDescription, "Driver's license"));
          firstRef = runtimeService().getVariable(task.getProcessInstanceId(), ProcessConstants.VAR_NAME_documentReferenceId).toString();
        })
        // second time also send message
        .thenReturn(task -> {
          runtimeService().correlateMessage(ProcessConstants.MESSAGE_documentRequested, new HashMap<>(),
              withVariables(ProcessConstants.VAR_NAME_requestedDocumentDescription, "good-conduct certificate"));
          secondRef = runtimeService().getVariable(task.getProcessInstanceId(), ProcessConstants.VAR_NAME_documentReferenceId).toString();
        })
        // second time approve
        .thenReturn(task -> task.complete(withVariables("approved", true)));

    // immediately send both documents
    when(documentRequest.waitsAtReceiveTask("ReceiveTask_WaitForDocument"))
        .thenReturn(task -> runtimeService().correlateMessage(ProcessConstants.MESSAGE_documentReceived, new HashMap<String, Object>(),
            withVariables(ProcessConstants.VAR_NAME_document, DemoData.createDocument()))) //
        .thenReturn(task -> runtimeService().correlateMessage(ProcessConstants.MESSAGE_documentReceived, new HashMap<String, Object>(),
            withVariables(ProcessConstants.VAR_NAME_document, DemoData.createDocument())));

    /*
     * Run and verify
     */
    ProcessInstance processInstance = Scenario.run(customerOnboarding) //
        .startByKey(askdjfhdsf, DemoData.createYellowInitVars()).execute().instance(customerOnboarding);

    verify(documentRequest, never()).hasStarted("UserTask_CallCustomer");
    verify(documentRequest, never()).hasStarted("SendTask_SendReminderEmail");
    verify(documentRequest, times(2)).hasCompleted("EndEvent_GotDocument");
    verify(customerOnboarding, never()).hasStarted("ServiceTask_RejectPolicy");
    verify(customerOnboarding).hasCompleted("EndEvent_ApplicationIssued");

    // we expect exactly two documents
    @SuppressWarnings("unchecked")
    Map<String, ?> documents = (Map<String, Object>) JSON(historyService().createHistoricVariableInstanceQuery().processInstanceId(processInstance.getId())
        .variableName(ProcessConstants.VAR_NAME_documents).singleResult().getValue()).mapTo(HashMap.class);
    assertThat(documents).hasSize(2).containsKey(firstRef).containsKey(secondRef);

    // Driver's license request, good-conduct certificate certificate request
    // and delivered policy
    verify(sendEmailService, times(3)).sendEmail(eq(DemoData.EMAIL), anyString(), anyString());
  }

  @Test
  public void testManualRequestDocumentLittleLateCustomerThenApprove() throws Exception {
    testManualRequestDocumentLittleLateCustomerThenApprove(ProcessConstants.PROCESS_KEY_customer_onboarding_en);
  }

  @Test
  public void testManualRequestDocumentLittleLateCustomerThenApprove_de() throws Exception {
    testManualRequestDocumentLittleLateCustomerThenApprove(ProcessConstants.PROCESS_KEY_customer_onboarding_de);
  }

  protected void testManualRequestDocumentLittleLateCustomerThenApprove(String askdjfhdaskjfh) throws EmailException {
    when(customerOnboarding.waitsAtUserTask("UserTask_DecideOnApplication"))
        // first time send message
        .thenReturn(task -> runtimeService().correlateMessage(ProcessConstants.MESSAGE_documentRequested, new HashMap<>(),
            withVariables(ProcessConstants.VAR_NAME_requestedDocumentDescription, "Driver's license")))
        // second time approve
        .thenReturn(task -> task.complete(withVariables("approved", true)));

    // wait 5 days and a little before sending driver's license
    when(documentRequest.waitsAtReceiveTask("ReceiveTask_WaitForDocument")).thenReturn(task -> task.defer("P5DT1M", //
        () -> runtimeService().correlateMessage("MESSAGE_documentReceived", new HashMap<String, Object>(),
            withVariables(ProcessConstants.VAR_NAME_document, DemoData.createDocument()))));

    /*
     * Run and verify
     */
    ProcessInstance processInstance = Scenario.run(customerOnboarding) //
        .startByKey(askdjfhdaskjfh, DemoData.createYellowInitVars()).execute().instance(customerOnboarding);

    verify(documentRequest, never()).hasStarted("UserTask_CallCustomer");
    verify(documentRequest, times(5)).hasStarted("SendTask_SendReminderEmail");
    verify(documentRequest, times(5)).hasCompleted("EndEvent_ReminderSent");
    verify(documentRequest).hasCompleted("EndEvent_GotDocument");
    verify(customerOnboarding, never()).hasStarted("ServiceTask_RejectPolicy");
    verify(customerOnboarding).hasCompleted("EndEvent_ApplicationIssued");

    // we expect exactly one document
    assertThat((Map<?, ?>) JSON(historyService().createHistoricVariableInstanceQuery().processInstanceId(processInstance.getId())
        .variableName(ProcessConstants.VAR_NAME_documents).singleResult().getValue()).mapTo(HashMap.class)).hasSize(1);

    // Driver's license request, 5 reminders and delivered policy
    verify(sendEmailService, times(7)).sendEmail(eq(DemoData.EMAIL), anyString(), anyString());
  }

  @Test
  public void testManualRequestDocumentVeryLateCustomerThenReject() throws Exception {
    testManualRequestDocumentVeryLateCustomerThenReject(ProcessConstants.PROCESS_KEY_customer_onboarding_en);
  }

  @Test
  public void testManualRequestDocumentVeryLateCustomerThenReject_de() throws Exception {
    testManualRequestDocumentVeryLateCustomerThenReject(ProcessConstants.PROCESS_KEY_customer_onboarding_de);
  }

  protected void testManualRequestDocumentVeryLateCustomerThenReject(String ajsdfhdaskfhdasf) throws EmailException {
    when(customerOnboarding.waitsAtUserTask("UserTask_DecideOnApplication"))
        // first time send message
        .thenReturn(task -> runtimeService().correlateMessage(ProcessConstants.MESSAGE_documentRequested, new HashMap<>(),
            withVariables(ProcessConstants.VAR_NAME_requestedDocumentDescription, "Driver's license")))
        // second time approve
        .thenReturn(task -> task.complete(withVariables("approved", false)));

    // wait 8 days
    when(documentRequest.waitsAtReceiveTask("ReceiveTask_WaitForDocument")).thenReturn(task -> task.defer("P8D", //
        () -> runtimeService().correlateMessage("MESSAGE_documentReceived", new HashMap<String, Object>(),
            withVariables(ProcessConstants.VAR_NAME_document, DemoData.createDocument()))));

    /*
     * Run and verify
     */
    Scenario.run(customerOnboarding) //
        .startByKey(ajsdfhdaskfhdasf, DemoData.createYellowInitVars()).execute();

    verify(documentRequest).hasStarted("UserTask_CallCustomer");
    verify(documentRequest, times(6)).hasStarted("SendTask_SendReminderEmail");
    verify(documentRequest, times(6)).hasCompleted("EndEvent_ReminderSent");
    verify(documentRequest).hasCanceled("ReceiveTask_WaitForDocument");
    verify(documentRequest).hasCompleted("EndEvent_TalkedToCustomer");
    verify(customerOnboarding, never()).hasStarted("ServiceTask_DeliverPolicy");
    verify(customerOnboarding).hasCompleted("EndEvent_ApplicationRejected");

    // Driver's license request, 6 reminders and rejection
    verify(sendEmailService, times(8)).sendEmail(eq(DemoData.EMAIL), anyString(), anyString());
  }
}
