package com.camunda.demo.insuranceapplication;

import static com.camunda.consulting.util.FilterGenerator.createFilter;
import static com.camunda.consulting.util.UserGenerator.addFilterGroupAuthorization;
import static com.camunda.consulting.util.UserGenerator.addGroup;
import static com.camunda.consulting.util.UserGenerator.addUser;
import static com.camunda.consulting.util.UserGenerator.createGrantGroupAuthorization;

import java.util.Arrays;
import java.util.List;

import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.ProcessApplicationReference;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.authorization.Permission;
import org.camunda.bpm.engine.authorization.Permissions;
import org.camunda.bpm.engine.authorization.Resources;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.DeploymentBuilder;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.variable.Variables;

import com.camunda.consulting.util.FilterGenerator;
import com.camunda.consulting.util.LicenseHelper;
import com.camunda.consulting.util.UserGenerator;
import com.camunda.demo.environment.DemoDataGenerator;

@ProcessApplication
public class InsuranceApplicationProcessApplication extends ServletProcessApplication {

  @PostDeploy
  public void setupEnvironmentForDemo(ProcessEngine engine) {
    LicenseHelper.setLicense(engine);
    // LicenseHelper.setLicense(engine);
    UserGenerator.createDefaultUsers(engine);
    setupUsersForDemo(engine);

    List<ProcessDefinition> isThereOldOne = engine.getRepositoryService().createProcessDefinitionQuery().processDefinitionKey("insurance_application_en")
        .list();

    DeploymentBuilder deploymentB = engine.getRepositoryService().createDeployment() //
        .addClasspathResource("risk_check_en.dmn") //
        .addClasspathResource("risk_check_de.dmn") //
        .addClasspathResource("document_request_en.bpmn") //
        .addClasspathResource("document_request_de.bpmn") //
        .enableDuplicateFiltering(true);
    if (isThereOldOne.isEmpty()) {
      deploymentB.addClasspathResource("insurance_application_old_en.bpmn") //
          .addClasspathResource("insurance_application_old_de.bpmn");
    }
    Deployment deployment = deploymentB.deploy();
    engine.getManagementService().registerProcessApplication(deployment.getId(), getReference());

    if (isThereOldOne.isEmpty()) {
      generateDataInOldModel(engine);
    }

    deployment = engine.getRepositoryService().createDeployment() //
        .addClasspathResource("insurance_application_en.bpmn") //
        .addClasspathResource("insurance_application_de.bpmn") //
        .enableDuplicateFiltering(true) //
        .deploy();
    engine.getManagementService().registerProcessApplication(deployment.getId(), getReference());

    generateDemoData(engine, getReference());
  }

  public static void generateDataInOldModel(ProcessEngine engine) {
    ((ProcessEngineConfigurationImpl) engine.getProcessEngineConfiguration()).getJobExecutor().shutdown();

    // push one instance to the first user task
    ProcessInstance pi = engine.getRuntimeService().startProcessInstanceByKey(ProcessConstants.PROCESS_KEY_insurance_application_en, "A-123",
        DemoData.createYellowInitVars());
    engine.getManagementService().executeJob(engine.getManagementService().createJobQuery().processInstanceId(pi.getId()).active().singleResult().getId());
    pi = engine.getRuntimeService().startProcessInstanceByKey(ProcessConstants.PROCESS_KEY_insurance_application_de, DemoData.createYellowInitVars());
    engine.getManagementService().executeJob(engine.getManagementService().createJobQuery().processInstanceId(pi.getId()).active().singleResult().getId());

    // and the other one to the second
    pi = engine.getRuntimeService().startProcessInstanceByKey(ProcessConstants.PROCESS_KEY_insurance_application_en, "A-456",DemoData.createYellowInitVars());
    engine.getManagementService().executeJob(engine.getManagementService().createJobQuery().processInstanceId(pi.getId()).active().singleResult().getId());
    Task decideOnApplication = engine.getTaskService().createTaskQuery().processInstanceId(pi.getId()).active().singleResult();
    engine.getTaskService().complete(decideOnApplication.getId(), Variables.putValue(ProcessConstants.VAR_NAME_approved, true));
    pi = engine.getRuntimeService().startProcessInstanceByKey(ProcessConstants.PROCESS_KEY_insurance_application_de, DemoData.createYellowInitVars());
    engine.getManagementService().executeJob(engine.getManagementService().createJobQuery().processInstanceId(pi.getId()).active().singleResult().getId());
    decideOnApplication = engine.getTaskService().createTaskQuery().processInstanceId(pi.getId()).active().singleResult();
    engine.getTaskService().complete(decideOnApplication.getId(), Variables.putValue(ProcessConstants.VAR_NAME_approved, true));

    ((ProcessEngineConfigurationImpl) engine.getProcessEngineConfiguration()).getJobExecutor().start();
  }

  public static void generateDemoData(ProcessEngine engine, ProcessApplicationReference reference) {
    DemoDataGenerator.autoGenerateFor(engine, ProcessConstants.PROCESS_KEY_insurance_application_en, reference, ProcessConstants.PROCESS_KEY_requestDocument_en,
        ProcessConstants.DECISION_KEY_checkRisk_en);
    DemoDataGenerator.autoGenerateFor(engine, ProcessConstants.PROCESS_KEY_insurance_application_de, reference, ProcessConstants.PROCESS_KEY_requestDocument_de,
        ProcessConstants.DECISION_KEY_checkRisk_de);

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
        new Permission[] { Permissions.READ, Permissions.READ_HISTORY, Permissions.UPDATE_INSTANCE }, Resources.PROCESS_DEFINITION,
        new String[] { "insurance_application_de" });

    createGrantGroupAuthorization(engine, new String[] { "clerk", "teamlead" },
        new Permission[] { Permissions.READ, Permissions.READ_HISTORY, Permissions.UPDATE_INSTANCE }, Resources.PROCESS_DEFINITION,
        new String[] { "insurance_application_en" });

    createGrantGroupAuthorization(engine, new String[] { "geschaeftsfuehrung" }, Permissions.values(), Resources.PROCESS_DEFINITION,
        new String[] { "insurance_application_de", "requestDocument_de" });

    createGrantGroupAuthorization(engine, new String[] { "management" }, Permissions.values(), Resources.PROCESS_DEFINITION,
        new String[] { "insurance_application_en", "requestDocument_en" });

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
