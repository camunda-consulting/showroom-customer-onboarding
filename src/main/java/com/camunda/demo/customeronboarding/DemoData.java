package com.camunda.demo.customeronboarding;

import static org.camunda.spin.Spin.JSON;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Map;

import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;
import org.camunda.bpm.engine.variable.value.FileValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.camunda.consulting.simulator.PayloadGenerator;
import com.camunda.consulting.simulator.SimulationExecutor;
import com.camunda.demo.customeronboarding.model.NewApplication;
import com.camunda.demo.customeronboarding.model.Person;

public class DemoData {

  static public class ContentGenerator extends PayloadGenerator {
    
    private final static Logger LOGGER = LoggerFactory.getLogger(ContentGenerator.class);
    
    public static long startedInstances = 0;
    
    public NewApplication newApplication(boolean german) {
      int birthYear = uniformBirthdate(16, 99).getYear();
      String category = uniformFromArgs4(Categorys.BASISPAKET.displayName, Categorys.STANDARDPAKET.displayName, Categorys.PREMIUMPAKET.displayName, Categorys.BASISPAKET.displayName);
      String employment = uniformFromArgs5(Employment.FEST_ANGESTELLT.displayName, Employment.FREELANCER.displayName, Employment.NICHT_ERWERBSTAETIG.displayName, Employment.SELBSTSTAENDIG.displayName, Employment.TEILZEIT.displayName);
      boolean male = uniformBoolean();
      String gender = male ? (german ? "Mann" : "male") : (german ? "Frau" : "female");
      String name = (male ? firstnameMale() : firstnameFemale()) + " " + (german ? surnameGerman() : surnameEnglish());
      String email = email(name, uniformFromArgs3("GoogleMail", "Hotmail", "Yahoo"));
      
      NewApplication a = (new DemoData()).createNeuantrag(birthYear, 
                      category,
                      employment, name, email, gender);
 
      startedInstances++;
      
      if(startedInstances % 100 == 0)
         LOGGER.info("Created " + new DecimalFormat("0000").format(startedInstances) + " Instances and progress: " + new DecimalFormat("000.##").format(SimulationExecutor.getProgress() * 100) + "!");
      
      return a;
    }
  }

  public static final String GENDER = "Mann";
  public static final String NAME = "Gentle Driver";
  public static final String EMAIL = "trashcan@camunda.org";

  public static NewApplication green() {
    return (new DemoData()).createNeuantrag(getBirthYear(0), Categorys.STANDARDPAKET.displayName, Employment.FEST_ANGESTELLT.displayName);
  }

  public static NewApplication yellow() {
    return (new DemoData()).createNeuantrag(getBirthYear(3), Categorys.PREMIUMPAKET.displayName, Employment.FREELANCER.displayName);
  }

  public static NewApplication red() {
    return (new DemoData()).createNeuantrag(getBirthYear(5), Categorys.PREMIUMPAKET.displayName , Employment.NICHT_ERWERBSTAETIG.displayName);
  }
  
  public static int getBirthYear(int endNumber) {
	  int thisYear = LocalDate.now().getYear();
	  int numberForCorrectEnding = - (20 + 10 - endNumber);
	  int numberForEvenDigits = thisYear % 10 >= 5 ? (thisYear % 10) - 10 : (thisYear % 10);
	  return LocalDate.now().plusYears(numberForCorrectEnding - numberForEvenDigits).getYear();
  }

  public static Map<String, Object> createGreenInitVars() {
    return createInitVars(green());
  }

  public static Map<String, Object> createYellowInitVars() {
    return createInitVars(yellow());
  }

  public static Map<String, Object> createRedInitVars() {
    return createInitVars(red());
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

  enum Categorys {
	  BASISPAKET("Basispaket"), STANDARDPAKET("Standard Paket"), PREMIUMPAKET("Premium Paket");
	  
	  private String displayName;

	  Categorys(String displayName) {
	        this.displayName = displayName;
	    }

	    public String displayName() { return displayName; }
  }
  
  enum Employment {
	  NICHT_ERWERBSTAETIG("Nicht erwerbstätig"), FREELANCER("Freelancer"), SELBSTSTAENDIG("Selbstständig"), FEST_ANGESTELLT("Fest angestellt"), TEILZEIT("Teilzeit");
	  
	  private String displayName;

	  Employment(String displayName) {
	        this.displayName = displayName;
	    }

	    public String displayName() { return displayName; }
  }
  

  public NewApplication createNeuantrag(int birthYear, String category, String employment, String name, String email, String gender) {
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
  
  public NewApplication createNeuantrag(int birthYear, String category, String employment) {
    return createNeuantrag(birthYear, category, employment, NAME, EMAIL, GENDER);
  }
}
