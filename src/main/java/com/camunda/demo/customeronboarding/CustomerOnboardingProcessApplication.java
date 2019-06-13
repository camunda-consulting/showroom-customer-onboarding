package com.camunda.demo.customeronboarding;

import static com.camunda.consulting.util.FilterGenerator.createFilter;
import static com.camunda.consulting.util.UserGenerator.addFilterGroupAuthorization;
import static com.camunda.consulting.util.UserGenerator.addGroup;
import static com.camunda.consulting.util.UserGenerator.addUser;
import static com.camunda.consulting.util.UserGenerator.createGrantGroupAuthorization;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Stream;

import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.ProcessApplicationReference;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.authorization.Permission;
import org.camunda.bpm.engine.authorization.Permissions;
import org.camunda.bpm.engine.authorization.Resources;
import org.camunda.bpm.engine.filter.Filter;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.DeploymentBuilder;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.repository.ResumePreviousBy;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.variable.Variables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.camunda.consulting.util.FilterGenerator;
import com.camunda.consulting.util.UserGenerator;
import com.camunda.demo.environment.DemoDataGenerator;

@ProcessApplication
public class CustomerOnboardingProcessApplication extends ServletProcessApplication {
  private static final Logger LOGGER = LoggerFactory.getLogger(CustomerOnboardingProcessApplication.class);

  @PostDeploy
  public void setupEnvironmentForDemo(ProcessEngine engine) {
    // this should be done by camunda-util-license-installer-war - if we have
    // both, DB exceptions may occur
    // LicenseHelper.setLicense(engine);
    UserGenerator.createDefaultUsers(engine);
    setupUsersForDemo(engine);

    List<ProcessDefinition> isThereOldOne = engine.getRepositoryService().createProcessDefinitionQuery().processDefinitionKey("customer_onboarding_en")
        .list();

    DeploymentBuilder deploymentBuilder = engine.getRepositoryService().createDeployment(getReference()) //
        .enableDuplicateFiltering(true) //
        .name(getName()) //
        .addClasspathResource("risk_check_en.dmn") //
        .addClasspathResource("risk_check_de.dmn") //
        .addClasspathResource("document_request_en.bpmn") //
        .addClasspathResource("document_request_de.bpmn") //
        .resumePreviousVersions() //
        .resumePreviousVersionsBy(ResumePreviousBy.RESUME_BY_PROCESS_DEFINITION_KEY)
        ;    
    Deployment deployment = deploymentBuilder.deploy();
    engine.getManagementService().registerProcessApplication(deployment.getId(), getReference());

//    if (isThereOldOne.isEmpty()) {
//      generateDataInOldModel(engine);
//    }

    deployment = engine.getRepositoryService().createDeployment(getReference()) //
        .enableDuplicateFiltering(true) //
        .name(getName()) //
        .addClasspathResource("customer_onboarding_en.bpmn") //
        .addClasspathResource("customer_onboarding_de.bpmn") //
        .resumePreviousVersions() //
        .resumePreviousVersionsBy(ResumePreviousBy.RESUME_BY_PROCESS_DEFINITION_KEY) //
        .deploy();
    engine.getManagementService().registerProcessApplication(deployment.getId(), getReference());

    new Timer().schedule(new TimerTask() {

      @Override
      public void run() {
        LOGGER.info("----                                                             ----");
        LOGGER.info("----                                                             ----");
        LOGGER.info("---- Starting demo data generation. PLEASE WAIT UNTIL IT'S DONE. ----");
        LOGGER.info("----                                                             ----");
        LOGGER.info("----                                                             ----");
        long begin = System.currentTimeMillis();
        long instances = generateDemoData(engine, getReference());
        long end = System.currentTimeMillis();
        LOGGER.info("----                                                     ----");
        LOGGER.info("----                                                     ----");
        LOGGER.info("---- Demo data generation for customer onboarding showcase DONE :) ----");
        LOGGER.info("----                                                     ----");
        LOGGER.info("----              .,      .           ,__                ----");
        LOGGER.info("----              | \\    / \\   |\\  |  |                  ----");
        LOGGER.info("----              |  |  |   |  | \\ |  |--                ----");
        LOGGER.info("----              | /    \\ /   |  \\|  |                  ----");
        LOGGER.info("----              ''      '           '--                ----");
        LOGGER.info("----                                                     ----");
        LOGGER.info("---- It took " + String.format("%02.1f", (end - begin) / 60_000d) + " minutes to start " + String.format("%05d", instances)
            + " instances.       ----");
      }
    }, 10_000);
  }

  public static void generateDataInOldModel(ProcessEngine engine) {
    ((ProcessEngineConfigurationImpl) engine.getProcessEngineConfiguration()).getJobExecutor().shutdown();

    // push one instance to the first user task
    ProcessInstance pi = engine.getRuntimeService().startProcessInstanceByKey(ProcessConstants.PROCESS_KEY_customer_onboarding_en, "A-123",
        DemoData.createYellowInitVars());
    engine.getManagementService().executeJob(engine.getManagementService().createJobQuery().processInstanceId(pi.getId()).active().singleResult().getId());
    pi = engine.getRuntimeService().startProcessInstanceByKey(ProcessConstants.PROCESS_KEY_customer_onboarding_de, DemoData.createYellowInitVars());
    engine.getManagementService().executeJob(engine.getManagementService().createJobQuery().processInstanceId(pi.getId()).active().singleResult().getId());

    // and the other one to the second
    pi = engine.getRuntimeService().startProcessInstanceByKey(ProcessConstants.PROCESS_KEY_customer_onboarding_en, "A-456", DemoData.createYellowInitVars());
    engine.getManagementService().executeJob(engine.getManagementService().createJobQuery().processInstanceId(pi.getId()).active().singleResult().getId());
    Task decideOnApplication = engine.getTaskService().createTaskQuery().processInstanceId(pi.getId()).active().singleResult();
    engine.getTaskService().complete(decideOnApplication.getId(), Variables.putValue(ProcessConstants.VAR_NAME_approved, true));
    pi = engine.getRuntimeService().startProcessInstanceByKey(ProcessConstants.PROCESS_KEY_customer_onboarding_de, DemoData.createYellowInitVars());
    engine.getManagementService().executeJob(engine.getManagementService().createJobQuery().processInstanceId(pi.getId()).active().singleResult().getId());
    decideOnApplication = engine.getTaskService().createTaskQuery().processInstanceId(pi.getId()).active().singleResult();
    engine.getTaskService().complete(decideOnApplication.getId(), Variables.putValue(ProcessConstants.VAR_NAME_approved, true));

    ((ProcessEngineConfigurationImpl) engine.getProcessEngineConfiguration()).getJobExecutor().start();
  }

  public static long generateDemoData(ProcessEngine engine, ProcessApplicationReference reference) {
    long startedInstances = 0;
    startedInstances += DemoDataGenerator.autoGenerateFor(engine, ProcessConstants.PROCESS_KEY_customer_onboarding_en, reference,
        ProcessConstants.PROCESS_KEY_requestDocument_en, ProcessConstants.DECISION_KEY_checkRisk_en);
    startedInstances += DemoDataGenerator.autoGenerateFor(engine, ProcessConstants.PROCESS_KEY_customer_onboarding_de, reference,
        ProcessConstants.PROCESS_KEY_requestDocument_de, ProcessConstants.DECISION_KEY_checkRisk_de);
    return startedInstances;
  }

  public static void setupUsersForDemo(ProcessEngine engine) {
    String myDe = createFilter(engine, "Persönlich", -10, "Meine persönlichen Aufgaben", //
        engine.getTaskService().createTaskQuery().taskAssigneeExpression("${currentUser()}").followUpBeforeOrNotExistentExpression("${now()}"));
    String myEn = createFilter(engine, "Personal", -10, "My personal tasks", //
        engine.getTaskService().createTaskQuery().taskAssigneeExpression("${currentUser()}").followUpBeforeOrNotExistentExpression("${now()}"));
    FilterGenerator.filterIds.put("myDe", myDe);
    FilterGenerator.filterIds.put("myEn", myEn);

    String groupDe = createFilter(engine, "Meine Gruppen", -20, "Aufgaben in allen meinen Gruppen", //
        engine.getTaskService().createTaskQuery().taskCandidateGroupInExpression("${currentUserGroups()}").taskUnassigned());
    String groupEn = createFilter(engine, "My groups", -20, "My group tasks", //
        engine.getTaskService().createTaskQuery().taskCandidateGroupInExpression("${currentUserGroups()}").taskUnassigned());
    FilterGenerator.filterIds.put("groupDe", groupDe);
    FilterGenerator.filterIds.put("groupEn", groupEn);

    String overdueDe = createFilter(engine, "Überfällig", 10, "Überfällige Aufgaben", //
        engine.getTaskService().createTaskQuery().taskAssigneeExpression("${currentUser()}").dueBeforeExpression("${now()}"), //
        "color", "#f2dede");
    String overdueEn = createFilter(engine, "Overdue", 10, "Overdue tasks", //
        engine.getTaskService().createTaskQuery().taskAssigneeExpression("${currentUser()}").dueBeforeExpression("${now()}"), //
        "color", "#f2dede");
    FilterGenerator.filterIds.put("overdueDe", overdueDe);
    FilterGenerator.filterIds.put("overdueEn", overdueEn);

    String followUpDe = createFilter(engine, "Wiedervorlage", 5, "Auf Wiedervorlage gelegte Aufgaben", //
        engine.getTaskService().createTaskQuery().taskAssigneeExpression("${currentUser()}").followUpAfterExpression("${now()}"));
    String followUpEn = createFilter(engine, "Follow-up", 5, "Follow-up tasks", //
        engine.getTaskService().createTaskQuery().taskAssigneeExpression("${currentUser()}").followUpAfterExpression("${now()}"));
    FilterGenerator.filterIds.put("followUpDe", followUpDe);
    FilterGenerator.filterIds.put("followUpEn", followUpEn);

    String managementDe = createFilter(engine, "Geschäftsführung", 0, "Aufgaben für 'Geschäftsführung'", //
        engine.getTaskService().createTaskQuery().taskCandidateGroupIn(Arrays.asList("geschaeftsfuehrung")).taskUnassigned());
    String managementEn = createFilter(engine, "Management", 0, "Tasks for 'Management'", //
        engine.getTaskService().createTaskQuery().taskCandidateGroupIn(Arrays.asList("management")).taskUnassigned());
    FilterGenerator.filterIds.put("managementDe", managementDe);
    FilterGenerator.filterIds.put("managementEn", managementEn);

    String allDe = createFilter(engine, "Alle Aufgaben", 20, "Alle Aufgaben", //
        engine.getTaskService().createTaskQuery());
    String allEn = createFilter(engine, "All tasks", 20, "All tasks", //
        engine.getTaskService().createTaskQuery());
    FilterGenerator.filterIds.put("allDe", allDe);
    FilterGenerator.filterIds.put("allEn", allEn);

    Stream.of(myDe, groupDe, overdueDe, followUpDe, managementDe, allDe).forEach(fId -> {
      Filter filter = engine.getFilterService().getFilter(fId);
      Map<String, Object> properties = filter.getProperties();
      HashMap<String, String> prop1 = new HashMap<String, String>();
      prop1.put("name", "applicationNumber");
      prop1.put("label", "Antrag-Nr.");
      HashMap<String, String> prop2 = new HashMap<String, String>();
      prop2.put("name", "applicantName");
      prop2.put("label", "Name");
      properties.put("variables", Arrays.asList(prop1, prop2));
      filter.setProperties(properties);
      engine.getFilterService().saveFilter(filter);
    });
    Stream.of(myEn, groupEn, overdueEn, followUpEn, managementEn, allEn).forEach(fId -> {
      Filter filter = engine.getFilterService().getFilter(fId);
      Map<String, Object> properties = filter.getProperties();
      HashMap<String, String> prop1 = new HashMap<String, String>();
      prop1.put("name", "applicationNumber");
      prop1.put("label", "Application-No.");
      HashMap<String, String> prop2 = new HashMap<String, String>();
      prop2.put("name", "applicantName");
      prop2.put("label", "Name");
      properties.put("variables", Arrays.asList(prop1, prop2));
      filter.setProperties(properties);
      engine.getFilterService().saveFilter(filter);
    });

    addUser(engine, "marc", "marc", "Marc", "Mustermann");
    addGroup(engine, "sachbearbeiter", "Sachbearbeiter", "marc");
    addFilterGroupAuthorization(engine, "sachbearbeiter", "myDe", "groupDe", "overdueDe", "followUpDe");

    addUser(engine, "ben", "ben", "Ben", "McKenzie");
    addGroup(engine, "clerk", "Clerk", "ben");
    addFilterGroupAuthorization(engine, "clerk", "myEn", "groupEn", "overdueEn", "followUpEn");

    addUser(engine, "hugo", "hugo", "Hugo", "Halbmann");
    addGroup(engine, "gruppenleiter", "Gruppenleiter", "hugo");
    addFilterGroupAuthorization(engine, "gruppenleiter", "myDe", "groupDe", "overdueDe", "followUpDe");

    addUser(engine, "lisa", "lisa", "Lisa", "Lacroix");
    addGroup(engine, "teamlead", "Team Lead", "lisa");
    addFilterGroupAuthorization(engine, "teamlead", "myEn", "groupEn", "overdueEn", "followUpEn");

    addUser(engine, "paul", "paul", "Paul", "Pohl");
    addGroup(engine, "geschaeftsfuehrung", "Geschäftsführung", "paul");
    addFilterGroupAuthorization(engine, "geschaeftsfuehrung", "myDe", "groupDe", "overdueDe", "followUpDe", "managementDe", "allDe");

    addUser(engine, "paula", "paula", "Paula", "Pepperman");
    addGroup(engine, "management", "Management", "paula");
    addFilterGroupAuthorization(engine, "management", "myEn", "groupEn", "overdueEn", "followUpEn", "managementEn", "allEn");

    createGrantGroupAuthorization(engine, new String[] { "sachbearbeiter", "gruppenleiter" },
        new Permission[] { Permissions.READ, Permissions.READ_HISTORY, Permissions.READ_INSTANCE, Permissions.UPDATE_INSTANCE }, Resources.PROCESS_DEFINITION,
        new String[] { "customer_onboarding_de" });

    createGrantGroupAuthorization(engine, new String[] { "clerk", "teamlead" },
        new Permission[] { Permissions.READ, Permissions.READ_HISTORY, Permissions.READ_INSTANCE, Permissions.UPDATE_INSTANCE }, Resources.PROCESS_DEFINITION,
        new String[] { "customer_onboarding_en" });

    createGrantGroupAuthorization(engine, new String[] { "geschaeftsfuehrung" }, Permissions.values(), Resources.PROCESS_DEFINITION,
        new String[] { "customer_onboarding_de", "requestDocument_de" });

    createGrantGroupAuthorization(engine, new String[] { "management" }, Permissions.values(), Resources.PROCESS_DEFINITION,
        new String[] { "customer_onboarding_en", "requestDocument_en" });

    createGrantGroupAuthorization(engine, new String[] { "geschaeftsfuehrung" }, Permissions.values(), Resources.DECISION_DEFINITION,
        new String[] { "checkRisk_de" });
    createGrantGroupAuthorization(engine, new String[] { "management" }, Permissions.values(), Resources.DECISION_DEFINITION, new String[] { "checkRisk_en" });
    createGrantGroupAuthorization(engine, new String[] { "geschaeftsfuehrung", "management" }, Permissions.values(), Resources.TASK, new String[] { "*" });
    createGrantGroupAuthorization(engine, new String[] { "geschaeftsfuehrung", "management" }, Permissions.values(), Resources.DEPLOYMENT,
        new String[] { "*" });
    createGrantGroupAuthorization(engine, new String[] { "geschaeftsfuehrung", "management" }, Permissions.values(), Resources.APPLICATION,
        new String[] { "cockpit" });
  }

}
