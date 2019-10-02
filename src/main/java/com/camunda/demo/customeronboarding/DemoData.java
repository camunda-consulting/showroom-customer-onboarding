package com.camunda.demo.customeronboarding;

import static com.camunda.consulting.util.FilterGenerator.createFilter;
import static com.camunda.consulting.util.UserGenerator.addFilterGroupAuthorization;
import static com.camunda.consulting.util.UserGenerator.addGroup;
import static com.camunda.consulting.util.UserGenerator.addUser;
import static com.camunda.consulting.util.UserGenerator.createGrantGroupAuthorization;
import static org.camunda.spin.Spin.JSON;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Stream;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.authorization.Permission;
import org.camunda.bpm.engine.authorization.Permissions;
import org.camunda.bpm.engine.authorization.Resources;
import org.camunda.bpm.engine.filter.Filter;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;
import org.camunda.bpm.engine.variable.value.FileValue;
import org.camunda.bpm.spring.boot.starter.event.ProcessApplicationStartedEvent;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.camunda.consulting.simulator.PayloadGenerator;
import com.camunda.consulting.simulator.SimulationExecutor;
import com.camunda.consulting.simulator.SimulatorPlugin;
import com.camunda.consulting.util.FilterGenerator;
import com.camunda.consulting.util.UserGenerator;
import com.camunda.demo.customeronboarding.DemoData.ContentGenerator;
import com.camunda.demo.customeronboarding.model.NewApplication;
import com.camunda.demo.customeronboarding.model.Person;

@Component
public class DemoData {
  
  private ProcessEngine processEngine;
  private DeploymentService deploymentService;
  
  @Autowired
  public DemoData (ProcessEngine processEngine, DeploymentService deploymentService) {
    this.processEngine = processEngine;
    this.deploymentService = deploymentService;
  }

  private final static Logger LOGGER = LoggerFactory.getLogger(DemoData.class);
  
  static public class ContentGenerator extends PayloadGenerator {
    
    private final static Logger LOGGER = LoggerFactory.getLogger(ContentGenerator.class);
    
    public static long startedInstances = 0;
    
    public NewApplication newApplication(boolean german) {
      NewApplication application = null;
      
      int rand = (int) (Math.random() * 10);
      
      if(rand < 3) {
        application = yellow(german);
      } else if (rand > 7) {
        application = red(german);
      } else {
        application = green(german);
      }

      boolean male = uniformBoolean();
      String gender = male ? (german ? "Mann" : "male") : (german ? "Frau" : "female");
      
      String name = (male ? firstnameMale() : firstnameFemale()) + " " + (german ? surnameGerman() : surnameEnglish());
      String email = email(name, uniformFromArgs3("GoogleMail", "Hotmail", "Yahoo"));
      
      application.getApplicant().setGender(gender);
      application.getApplicant().setName(name);
      application.getApplicant().setEmail(email);
      
 
      startedInstances++;
      
      if(startedInstances % 100 == 0)
         LOGGER.info("Created " + new DecimalFormat("0000").format(startedInstances) + " Instances and progress: " + new DecimalFormat("000.##").format(SimulationExecutor.getProgress() * 100) + "!");
      
      return application;
    }
    
  }

  public static final String GENDER = "Mann";
  public static final String NAME = "Gentle Driver";
  public static final String EMAIL = "trashcan@camunda.org";

  public static NewApplication green(boolean german) {
    String category = Categorys.values()[german ? getRandom(0, 2) :  getRandom(3, 5)].displayName;
    String employment = Employment.values()[german ? getRandom(1, 4) : getRandom(6, 8)].displayName;

    int addedDecades = 10 * getRandom(1, 4);     
    int endYear = getRandom(0, 3) == 3 ? 4 : getRandom(0, 1) == 0 ? getRandom(0, 4) : getRandom(8, 9);
    
    int birthYear = getBirthYear(0) - addedDecades - endYear;
   
    return createNeuantrag(birthYear, category, employment);
  }

  public static NewApplication yellow(boolean german) {
    NewApplication application = null;
    
    int rule = getRandom(0, 3);
    
    if(rule == 0) {
      application = ruleYellowFirst(german);      
    } else if (rule == 1) {
      application = ruleYellowSecond(german);
    } else if (rule == 2) {
      application = ruleYellowThird(german);
    } else {
      application = ruleYellowSecondAndThird(german);
    }
    
    return application;
  }
  
  static NewApplication ruleYellowFirst(boolean german) {
    String employment = german ? Employment.FEST_ANGESTELLT.displayName : Employment.SALARIED.displayName;
    String category = german ? Categorys.PREMIUMPAKET.displayName : Categorys.PREMIUM_PACKAGE.displayName;
    int birthYear = getBirthYear(5) - (10 * getRandom(1, 2));
    return createNeuantrag(birthYear, category, employment);
  }
  
  static NewApplication ruleYellowSecond(boolean german) {
    String employment = Employment.FREELANCER.displayName;
    String category = Categorys.values()[german ? getRandom(0, 2) :  getRandom(3, 5)].displayName;
    int birthYear = getBirthYear(3);
    return createNeuantrag(birthYear, category, employment);
  }
  
  
  static NewApplication ruleYellowThird(boolean german) {
    String employment = german ? Employment.SELBSTSTAENDIG.displayName : Employment.SELF_EMPLOYED.displayName;
    String category = german ? Categorys.PREMIUMPAKET.displayName : Categorys.PREMIUM_PACKAGE.displayName;
    int birthYear = getBirthYear(0);
    return createNeuantrag(birthYear, category, employment);
  }
  
  
  static NewApplication ruleYellowSecondAndThird(boolean german) {
    String employment = german ? Employment.SELBSTSTAENDIG.displayName : Employment.SELF_EMPLOYED.displayName;
    String category = german ? Categorys.PREMIUMPAKET.displayName : Categorys.PREMIUM_PACKAGE.displayName;
    int birthYear = getBirthYear(3);    
    return createNeuantrag(birthYear, category, employment);
  }
  

  public static NewApplication red(boolean german) {    
    int rule = getRandom(0, 1);
    
    NewApplication application = null;
    
    if(rule == 0) {
      application = ruleRedFirst(german);
    } else {
      application = ruleRedSecond(german);
    }
    
    return application;
  }
  
  static NewApplication ruleRedFirst(boolean german) {
    int rand = getRandom(0, 1);
    
    Categorys category = null;
    if(rand == 0) {
      category = german? Categorys.STANDARDPAKET : Categorys.STANDARD_PACKAGE;
    } else {
      category = german? Categorys.PREMIUMPAKET : Categorys.PREMIUM_PACKAGE;
    }
    
    Employment employment = german ? Employment.NICHT_ERWERBSTAETIG : Employment.UNEMPLOYED;
    int birthYear = getBirthYear(rand == 1 ? 3 : 5) - (getRandom(1, 4) * 10);
    return createNeuantrag(birthYear, category.displayName, employment.displayName);
  }
  
  static NewApplication ruleRedSecond(boolean german) {
    String category = Categorys.values()[german ? getRandom(0, 1) :  getRandom(3, 4)].displayName;
    String employment = Employment.values()[german ? getRandom(3, 4) : getRandom(7, 8)].displayName;
    return createNeuantrag(getBirthYear(5), category, employment);
  }
  
  static int getBirthYear(int endNumber) {
	  int thisYear = LocalDate.now().getYear();
	  int numberForCorrectEnding = - (20 + 10 - endNumber);
	  int numberForEvenDigits = thisYear % 10 >= 5 ? (thisYear % 10) - 10 : (thisYear % 10);
	  return LocalDate.now().plusYears(numberForCorrectEnding - numberForEvenDigits).getYear();
  }

  public static Map<String, Object> createGreenInitVars(boolean german) {
    return createInitVars(green(german));
  }

  public static Map<String, Object> createYellowInitVars(boolean german) {
    return createInitVars(yellow(german));
  }

  public static Map<String, Object> createRedInitVars(boolean german) {
    return createInitVars(red(german));
  }

  public static FileValue createDocument() {
    return Variables.fileValue("myDocument.txt").file("This is my very impressing driver's license.".getBytes()).mimeType("text/plain").create();
  }

  public static Map<String, Object> createInitVars(NewApplication application) {
    return Variables.createVariables() //
        .putValue(ProcessConstants.VAR_NAME_application, Variables.objectValue(application).serializationDataFormat(SerializationDataFormats.JSON).create()) //
        .putValue(ProcessConstants.VAR_NAME_documents, JSON("{}")) //
        .putValue(ProcessConstants.VAR_NAME_uiBaseUrl, "http://example.com/")
        .putValue(ProcessConstants.VAR_NAME_applicationNumber, application.getApplicationNumber())//
        .putValue(ProcessConstants.VAR_NAME_applicantName, application.getApplicant().getName())//
    ;
  }
  
  private static int getRandom(int from, int inclusiveTo) {
    return ((int) (Math.random() * (inclusiveTo - from + 1))) + from;
  }

  enum Categorys {
	  BASISPAKET("Basispaket"), STANDARDPAKET("Standard Paket"), PREMIUMPAKET("Premium Paket"),
	  BASIC_PACKAGE("Basic Package"), STANDARD_PACKAGE("Standard Package"), PREMIUM_PACKAGE("Premium Package");
	  
	  private String displayName;

	  Categorys(String displayName) {
	        this.displayName = displayName;
	    }

	    public String displayName() { return displayName; }
  }
  
  enum Employment {
	  NICHT_ERWERBSTAETIG("Nicht erwerbstätig"), FREELANCER("Freelancer"), SELBSTSTAENDIG("Selbstständig"), FEST_ANGESTELLT("Fest angestellt"), TEILZEIT("Teilzeit"),
	  UNEMPLOYED("Unemployed"), SELF_EMPLOYED("Self-employed"), SALARIED("Salaried"), PART_TIME("Part-time");
	  
	  private String displayName;

	  Employment(String displayName) {
	        this.displayName = displayName;
	    }

	    public String displayName() { return displayName; }
  }
  

  public static NewApplication createNeuantrag(int birthYear, String category, String employment, String name, String email, String gender) {
    NewApplication newApplication = new NewApplication();
    newApplication.setApplicant(new Person());

    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.YEAR, birthYear);

    newApplication.getApplicant().setBirthday(cal.getTime());
    newApplication.getApplicant().setName(name);
    newApplication.getApplicant().setEmail(email);
    newApplication.getApplicant().setGender(gender);
    newApplication.setCategory(category);
    newApplication.setEmployment(employment);
    newApplication.setPriceIndicationInCent(32000);
    newApplication.setCorporation("Camunbankia");
    newApplication.setProduct("super cool product");
    return newApplication;
  }
  
  public static NewApplication createNeuantrag(int birthYear, String category, String employment) {
    return createNeuantrag(birthYear, category, employment, NAME, EMAIL, GENDER);
  }
  
  private void generateDataInOldModel(ProcessEngine processEngine) {
    // push one instance to the first user task
    ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey(ProcessConstants.PROCESS_KEY_customer_onboarding_en, "A-123",
        DemoData.createYellowInitVars(false));
    processEngine.getManagementService().executeJob(processEngine.getManagementService().createJobQuery().processInstanceId(pi.getId()).active().singleResult().getId());
    
    pi = processEngine.getRuntimeService().startProcessInstanceByKey(ProcessConstants.PROCESS_KEY_customer_onboarding_de, DemoData.createYellowInitVars(true));
    processEngine.getManagementService().executeJob(processEngine.getManagementService().createJobQuery().processInstanceId(pi.getId()).active().singleResult().getId());

    // and the other one to the second
    pi = processEngine.getRuntimeService().startProcessInstanceByKey(ProcessConstants.PROCESS_KEY_customer_onboarding_en, "A-456", DemoData.createYellowInitVars(false));
    processEngine.getManagementService().executeJob(processEngine.getManagementService().createJobQuery().processInstanceId(pi.getId()).active().singleResult().getId());
    Task decideOnApplication = processEngine.getTaskService().createTaskQuery().processInstanceId(pi.getId()).active().singleResult();
    processEngine.getTaskService().complete(decideOnApplication.getId(), Variables.putValue(ProcessConstants.VAR_NAME_approved, true));
    
    pi = processEngine.getRuntimeService().startProcessInstanceByKey(ProcessConstants.PROCESS_KEY_customer_onboarding_de, DemoData.createYellowInitVars(true));
    processEngine.getManagementService().executeJob(processEngine.getManagementService().createJobQuery().processInstanceId(pi.getId()).active().singleResult().getId());
    decideOnApplication = processEngine.getTaskService().createTaskQuery().processInstanceId(pi.getId()).active().singleResult();
    processEngine.getTaskService().complete(decideOnApplication.getId(), Variables.putValue(ProcessConstants.VAR_NAME_approved, true));
  }

  private void setupUsersForDemo(ProcessEngine processEngine) {
    String myDe = createFilter(processEngine, "Persönlich", -10, "Meine persönlichen Aufgaben", //
        processEngine.getTaskService().createTaskQuery().taskAssigneeExpression("${currentUser()}").followUpBeforeOrNotExistentExpression("${now()}"));
    String myEn = createFilter(processEngine, "Personal", -10, "My personal tasks", //
        processEngine.getTaskService().createTaskQuery().taskAssigneeExpression("${currentUser()}").followUpBeforeOrNotExistentExpression("${now()}"));
    FilterGenerator.filterIds.put("myDe", myDe);
    FilterGenerator.filterIds.put("myEn", myEn);

    String groupDe = createFilter(processEngine, "Meine Gruppen", -20, "Aufgaben in allen meinen Gruppen", //
        processEngine.getTaskService().createTaskQuery().taskCandidateGroupInExpression("${currentUserGroups()}").taskUnassigned());
    String groupEn = createFilter(processEngine, "My groups", -20, "My group tasks", //
        processEngine.getTaskService().createTaskQuery().taskCandidateGroupInExpression("${currentUserGroups()}").taskUnassigned());
    FilterGenerator.filterIds.put("groupDe", groupDe);
    FilterGenerator.filterIds.put("groupEn", groupEn);

    String overdueDe = createFilter(processEngine, "Überfällig", 10, "Überfällige Aufgaben", //
        processEngine.getTaskService().createTaskQuery().taskAssigneeExpression("${currentUser()}").dueBeforeExpression("${now()}"), //
        "color", "#f2dede");
    String overdueEn = createFilter(processEngine, "Overdue", 10, "Overdue tasks", //
        processEngine.getTaskService().createTaskQuery().taskAssigneeExpression("${currentUser()}").dueBeforeExpression("${now()}"), //
        "color", "#f2dede");
    FilterGenerator.filterIds.put("overdueDe", overdueDe);
    FilterGenerator.filterIds.put("overdueEn", overdueEn);

    String followUpDe = createFilter(processEngine, "Wiedervorlage", 5, "Auf Wiedervorlage gelegte Aufgaben", //
        processEngine.getTaskService().createTaskQuery().taskAssigneeExpression("${currentUser()}").followUpAfterExpression("${now()}"));
    String followUpEn = createFilter(processEngine, "Follow-up", 5, "Follow-up tasks", //
        processEngine.getTaskService().createTaskQuery().taskAssigneeExpression("${currentUser()}").followUpAfterExpression("${now()}"));
    FilterGenerator.filterIds.put("followUpDe", followUpDe);
    FilterGenerator.filterIds.put("followUpEn", followUpEn);

    String managementDe = createFilter(processEngine, "Geschäftsführung", 0, "Aufgaben für 'Geschäftsführung'", //
        processEngine.getTaskService().createTaskQuery().taskCandidateGroupIn(Arrays.asList("geschaeftsfuehrung")).taskUnassigned());
    String managementEn = createFilter(processEngine, "Management", 0, "Tasks for 'Management'", //
        processEngine.getTaskService().createTaskQuery().taskCandidateGroupIn(Arrays.asList("management")).taskUnassigned());
    FilterGenerator.filterIds.put("managementDe", managementDe);
    FilterGenerator.filterIds.put("managementEn", managementEn);

    String allDe = createFilter(processEngine, "Alle Aufgaben", 20, "Alle Aufgaben", //
        processEngine.getTaskService().createTaskQuery());
    String allEn = createFilter(processEngine, "All tasks", 20, "All tasks", //
        processEngine.getTaskService().createTaskQuery());
    FilterGenerator.filterIds.put("allDe", allDe);
    FilterGenerator.filterIds.put("allEn", allEn);

    Stream.of(myDe, groupDe, overdueDe, followUpDe, managementDe, allDe).forEach(fId -> {
      Filter filter = processEngine.getFilterService().getFilter(fId);
      Map<String, Object> properties = filter.getProperties();
      HashMap<String, String> prop1 = new HashMap<String, String>();
      prop1.put("name", "applicationNumber");
      prop1.put("label", "Antrag-Nr.");
      HashMap<String, String> prop2 = new HashMap<String, String>();
      prop2.put("name", "applicantName");
      prop2.put("label", "Name");
      HashMap<String, String> prop3 = new HashMap<String, String>();
      prop3.put("name", "corporation");
      prop3.put("label", "Firma");
      properties.put("variables", Arrays.asList(prop1, prop2, prop3));
      filter.setProperties(properties);
      processEngine.getFilterService().saveFilter(filter);
    });
    Stream.of(myEn, groupEn, overdueEn, followUpEn, managementEn, allEn).forEach(fId -> {
      Filter filter = processEngine.getFilterService().getFilter(fId);
      Map<String, Object> properties = filter.getProperties();
      HashMap<String, String> prop1 = new HashMap<String, String>();
      prop1.put("name", "applicationNumber");
      prop1.put("label", "Application-No.");
      HashMap<String, String> prop2 = new HashMap<String, String>();
      prop2.put("name", "applicantName");
      prop2.put("label", "Name");
      HashMap<String, String> prop3 = new HashMap<String, String>();
      prop3.put("name", "corporation");
      prop3.put("label", "Company");
      properties.put("variables", Arrays.asList(prop1, prop2, prop3));
      filter.setProperties(properties);
      processEngine.getFilterService().saveFilter(filter);
    });

    addUser(processEngine, "marc", "marc", "Marc", "Mustermann");
    addGroup(processEngine, "sachbearbeiter", "Sachbearbeiter", "marc");
    addFilterGroupAuthorization(processEngine, "sachbearbeiter", "myDe", "groupDe", "overdueDe", "followUpDe");

    addUser(processEngine, "ben", "ben", "Ben", "McKenzie");
    addGroup(processEngine, "clerk", "Clerk", "ben");
    addFilterGroupAuthorization(processEngine, "clerk", "myEn", "groupEn", "overdueEn", "followUpEn");

    addUser(processEngine, "hugo", "hugo", "Hugo", "Halbmann");
    addGroup(processEngine, "gruppenleiter", "Gruppenleiter", "hugo");
    addFilterGroupAuthorization(processEngine, "gruppenleiter", "myDe", "groupDe", "overdueDe", "followUpDe");

    addUser(processEngine, "lisa", "lisa", "Lisa", "Lacroix");
    addGroup(processEngine, "teamlead", "Team Lead", "lisa");
    addFilterGroupAuthorization(processEngine, "teamlead", "myEn", "groupEn", "overdueEn", "followUpEn");

    addUser(processEngine, "paul", "paul", "Paul", "Pohl");
    addGroup(processEngine, "geschaeftsfuehrung", "Geschäftsführung", "paul");
    addFilterGroupAuthorization(processEngine, "geschaeftsfuehrung", "myDe", "groupDe", "overdueDe", "followUpDe", "managementDe", "allDe");

    addUser(processEngine, "paula", "paula", "Paula", "Pepperman");
    addGroup(processEngine, "management", "Management", "paula");
    addFilterGroupAuthorization(processEngine, "management", "myEn", "groupEn", "overdueEn", "followUpEn", "managementEn", "allEn");

    createGrantGroupAuthorization(processEngine, new String[] { "sachbearbeiter", "gruppenleiter" },
        new Permission[] { Permissions.READ, Permissions.READ_HISTORY, Permissions.READ_INSTANCE, Permissions.UPDATE_INSTANCE }, Resources.PROCESS_DEFINITION,
        new String[] { "customer_onboarding_de" });

    createGrantGroupAuthorization(processEngine, new String[] { "clerk", "teamlead" },
        new Permission[] { Permissions.READ, Permissions.READ_HISTORY, Permissions.READ_INSTANCE, Permissions.UPDATE_INSTANCE }, Resources.PROCESS_DEFINITION,
        new String[] { "customer_onboarding_en" });

    Permission[] allPermissions = new Permissions[] {Permissions.ALL};
    
    createGrantGroupAuthorization(processEngine, new String[] { "geschaeftsfuehrung" }, allPermissions, Resources.PROCESS_DEFINITION,
        new String[] { "customer_onboarding_de", "requestDocument_de" });

    createGrantGroupAuthorization(processEngine, new String[] { "management" }, allPermissions, Resources.PROCESS_DEFINITION,
        new String[] { "customer_onboarding_en", "requestDocument_en" });

    createGrantGroupAuthorization(processEngine, new String[] { "geschaeftsfuehrung" }, allPermissions, Resources.DECISION_DEFINITION,
        new String[] { "checkRisk_de" });
    createGrantGroupAuthorization(processEngine, new String[] { "management" }, allPermissions, Resources.DECISION_DEFINITION, new String[] { "checkRisk_en" });
    createGrantGroupAuthorization(processEngine, new String[] { "geschaeftsfuehrung", "management" }, allPermissions, Resources.TASK, new String[] { "*" });
    createGrantGroupAuthorization(processEngine, new String[] { "geschaeftsfuehrung", "management" }, allPermissions, Resources.DEPLOYMENT,
        new String[] { "*" });
    createGrantGroupAuthorization(processEngine, new String[] { "geschaeftsfuehrung", "management" }, allPermissions, Resources.APPLICATION,
        new String[] { "cockpit" });
  }
  

  public void setupEnvironmentForDemo(ProcessEngine processEngine) {
    // this should be done by camunda-util-license-installer-war - if we have
    // both, DB exceptions may occur
    // LicenseHelper.setLicense(engine);
    if(!oldDataExists()) {
      if(!oldDeploymentExists()) {
        deploymentService.deployStandardProcesses();
        UserGenerator.createDefaultUsers(processEngine);
        setupUsersForDemo(processEngine);
      }
      generateDataInOldModel(processEngine);
    }
    LOGGER.info("Data for old instances generated.");
    deploymentService.deployCustomerOnboardCurrent();
    
    runSimulationAndStopAfterwards();
    
  }  
  
  private void runSimulationAndStopAfterwards() {
    new Timer().schedule(new TimerTask() {

      @Override
      public void run() {
        DemoData.LOGGER.info("----                                                             ----");
        LOGGER.info("----                                                             ----");
        LOGGER.info("---- Starting demo data generation. PLEASE WAIT UNTIL IT'S DONE. ----");
        LOGGER.info("----                                                             ----");
        LOGGER.info("----                                                             ----");
        long begin = System.currentTimeMillis();
        //SimulationExecutor.execute(DateTime.now().minusMonths(12).toDate(), DateTime.now().toDate());
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
        LOGGER.info("---- It took " + String.format("%02.1f", (end - begin) / 60_000d) + " minutes to start " + String.format("%05d", ContentGenerator.startedInstances)
            + " instances.       ----");
//        SimulationExecutor.stopSimulation();
        SimulatorPlugin.resetProcessEngineElements();
        deploymentService.deployAllCurrent();
        LOGGER.info("Redeployment finished");
      }
    }, 10_000);
  }
  
  private boolean oldDeploymentExists() {
    return !processEngine.getRepositoryService().createProcessDefinitionQuery().processDefinitionKey("customer_onboarding_en").list().isEmpty();
  }
  
  private boolean oldDataExists() {
    boolean exists = false;
    List<ProcessDefinition> processDefList = processEngine.getRepositoryService().createProcessDefinitionQuery().processDefinitionKey("customer_onboarding_en").list();
    for(ProcessDefinition processDefiniton : processDefList) {
      if(processDefiniton.getVersion() == 1) {
        exists = !processEngine.getRuntimeService().createProcessInstanceQuery().processDefinitionKey(processDefiniton.getKey()).list().isEmpty();
      }
    }
    return exists; 
  }
  
  @EventListener
  public void notify(final ProcessApplicationStartedEvent processApplicationStartedEvent) {
    setupEnvironmentForDemo(processEngine);
  }
  
}
