package com.camunda.demo.insuranceapplication;

import static org.camunda.spin.Spin.JSON;

import java.util.Calendar;
import java.util.Map;

import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;
import org.camunda.bpm.engine.variable.value.FileValue;

import com.camunda.demo.environment.simulation.DefaultContentGenerator;
import com.camunda.demo.insuranceapplication.model.NewApplication;
import com.camunda.demo.insuranceapplication.model.Person;

public class DemoData {

  static public class ContentGenerator extends DefaultContentGenerator {
    public NewApplication newApplication(boolean german) {
      Person p = new Person();
      p.setBirthday(uniformBirthdate(16, 99));
      boolean male = uniformBoolean();
      if (german) {
        p.setSex(male ? "Mann" : "Frau");
        p.setName((male ? firstnameMale() : firstnameFemale()) + " " + surnameGerman());
      } else {
        p.setSex(male ? "male" : "female");
        p.setName((male ? firstnameMale() : firstnameFemale()) + " " + surnameEnglish());
      }
      p.setEmail(email(p.getName(), uniformFromArgs3("GoogleMail", "Hotmail", "Yahoo")));

      NewApplication a = new NewApplication(businessKey());
      a.setApplicant(p);

      a.setVehicleManufacturer(uniformFromArgs4("VW", "BMW", "Porsche", "Audi"));
      switch (a.getVehicleManufacturer()) {
      case "VW":
        a.setVehicleType(uniformFromArgs4("Beatle", "Golf IV", "Golf V", "Passat"));
        switch (a.getVehicleType()) {
        case "Beatle":
          a.setPriceIndicationInCent(120);
          break;
        case "Golf IV":
          a.setPriceIndicationInCent(160);
          break;
        case "Golf V":
          a.setPriceIndicationInCent(150);
          break;
        case "Passat":
          a.setPriceIndicationInCent(150);
          break;

        default:
          break;
        }
        break;

      case "BMW":
        a.setVehicleType(uniformFromArgs4("318i", "525i", "735i", "X3"));
        switch (a.getVehicleType()) {
        case "318i":
          a.setPriceIndicationInCent(190);
          break;
        case "525i":
          a.setPriceIndicationInCent(210);
          break;
        case "735i":
          a.setPriceIndicationInCent(240);
          break;
        case "X3":
          a.setPriceIndicationInCent(280);
          break;

        default:
          break;
        }
        break;

      case "Porsche":
        a.setVehicleType(uniformFromArgs4("911", "925", "Boxster", "Cayenne"));
        switch (a.getVehicleType()) {
        case "911":
          a.setPriceIndicationInCent(310);
          break;
        case "925":
          a.setPriceIndicationInCent(300);
          break;
        case "Boxster":
          a.setPriceIndicationInCent(290);
          break;
        case "Cayenne":
          a.setPriceIndicationInCent(300);
          break;

        default:
          break;
        }
        break;

      case "Audi":
        a.setVehicleType(uniformFromArgs4("A3", "A4", "A6", "A8"));
        switch (a.getVehicleType()) {
        case "A3":
          a.setPriceIndicationInCent(180);
          break;
        case "A4":
          a.setPriceIndicationInCent(180);
          break;
        case "A6":
          a.setPriceIndicationInCent(200);
          break;
        case "A8":
          a.setPriceIndicationInCent(280);
          break;

        default:
          break;
        }
        break;

      default:
        break;
      }
      a.setPriceIndicationInCent(100 * a.getPriceIndicationInCent());
      a.setPremiumInCent(a.getPriceIndicationInCent());

      a.setProduct("Camundanzia Vollkasko Plus");

      return a;
    }
  }

  public static final String PRODUCT = "Camundanzia Vollkasko Plus";
  public static final String SEX = "Mann";
  public static final String NAME = "Gentle Driver";
  public static final String EMAIL = "trashcan@camunda.org";

  public static NewApplication green() {
    return createNeuantrag(40, "VW", "Golf V");
  }

  public static NewApplication yellow() {
    return createNeuantrag(40, "BMW", "X3");
  }

  public static NewApplication red() {
    return createNeuantrag(20, "Porsche", "911");
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

  public static NewApplication createNeuantrag(int alter, String hersteller, String typ) {
    NewApplication newApplication = new NewApplication();
    newApplication.setApplicant(new Person());

    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.YEAR, -1 * alter);

    newApplication.getApplicant().setBirthday(cal.getTime());
    newApplication.getApplicant().setName(NAME);
    newApplication.getApplicant().setEmail(EMAIL);
    newApplication.getApplicant().setSex(SEX);
    newApplication.setVehicleManufacturer(hersteller);
    newApplication.setVehicleType(typ);
    newApplication.setProduct(PRODUCT);
    newApplication.setPriceIndicationInCent(32000);
    return newApplication;
  }
}