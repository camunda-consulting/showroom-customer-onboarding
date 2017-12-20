package com.camunda.demo.insuranceapplication;

import static org.camunda.spin.Spin.JSON;

import java.util.Calendar;
import java.util.Map;

import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;
import org.camunda.bpm.engine.variable.value.FileValue;

import com.camunda.demo.insuranceapplication.model.NewApplication;
import com.camunda.demo.insuranceapplication.model.Person;

public class DemoData {
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
        .putValue(ProcessConstants.VAR_NAME_uiBaseUrl, "http://example.com/");
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