package com.camunda.demo.customeronboarding;

import static org.camunda.spin.Spin.JSON;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Map;

import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;
import org.camunda.bpm.engine.variable.value.FileValue;

import com.camunda.demo.environment.simulation.DefaultContentGenerator;
import com.camunda.demo.customeronboarding.model.NewApplication;
import com.camunda.demo.customeronboarding.model.Person;

public class DemoData {

  static public class ContentGenerator extends DefaultContentGenerator {
    public NewApplication newApplication(boolean german) {
      Person p = new Person();
      p.setBirthday(uniformBirthdate(16, 99));
      boolean male = uniformBoolean();
      if (german) {
        p.setGender(male ? "Mann" : "Frau");
        p.setName((male ? firstnameMale() : firstnameFemale()) + " " + surnameGerman());
      } else {
        p.setGender(male ? "male" : "female");
        p.setName((male ? firstnameMale() : firstnameFemale()) + " " + surnameEnglish());
      }
      p.setEmail(email(p.getName(), uniformFromArgs3("GoogleMail", "Hotmail", "Yahoo")));

      NewApplication a = new NewApplication(businessKey());
      a.setApplicant(p);
      
      a.setPriceIndication("333");
      a.setCategory(Categorys.BASISPAKET.displayName);//uniformFromArgs4(Categorys.BASISPAKET.displayName, Categorys.STANDARDPAKET.displayName, Categorys.PREMIUMPAKET, Categorys.BASISPAKET.displayName));

      a.setPriceIndicationInCent(100 * a.getPriceIndicationInCent());
      a.setPremiumInCent(a.getPriceIndicationInCent());

      return a;
    }
  }

  public static final String GENDER = "Mann";
  public static final String NAME = "Gentle Driver";
  public static final String EMAIL = "trashcan@camunda.org";

  public static NewApplication green() {
    return createNeuantrag(getBirthYear(0), Categorys.STANDARDPAKET.displayName, Employment.FEST_ANGESTELLT.displayName);
  }

  public static NewApplication yellow() {
    return createNeuantrag(getBirthYear(3), Categorys.PREMIUMPAKET.displayName, Employment.FREELANCER.displayName);
  }

  public static NewApplication red() {
    return createNeuantrag(getBirthYear(5), Categorys.PREMIUMPAKET.displayName , Employment.NICHT_ERWERBSTAETIG.displayName);
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
  
  public static NewApplication createNeuantrag(int birthYear, String category, String employment) {
    NewApplication newApplication = new NewApplication();
    newApplication.setApplicant(new Person());

    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.YEAR, birthYear);

    newApplication.getApplicant().setBirthday(cal.getTime());
    newApplication.getApplicant().setName(NAME);
    newApplication.getApplicant().setEmail(EMAIL);
    newApplication.getApplicant().setGender(GENDER);
    newApplication.setCategory(category);
    newApplication.setEmployment(employment);
    newApplication.setPriceIndicationInCent(32000);
    newApplication.setCorporation("Camunbankia");
    return newApplication;
  }
}
