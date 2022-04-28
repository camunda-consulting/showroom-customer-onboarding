package com.camunda.demo.customeronboarding;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.camunda.demo.customeronboarding.model.NewApplication;
import com.camunda.demo.customeronboarding.model.Person;

public class TestDataUtil {

    public static final String GENDER = "Male";
    public static final String NAME = "Good driver";
    public static final String EMAIL = "good.driver@camunda.org";


    public static NewApplication greenApplication() {
        NewApplication application = new NewApplication();
        application.setApplicant(new Person());
        application.getApplicant().setAge(35);
        application.getApplicant().setBirthday(getDate(1994, 9, 11));
        application.getApplicant().setName(NAME);
        application.getApplicant().setEmail(EMAIL);
        application.getApplicant().setGender(GENDER);
        application.setUiBaseUrl("http://localhost:8080/camunda/online/banking/");
        application.setCorporation("Camunbankia");
        application.setCategory(Categorys.STANDARD_PACKAGE.displayName());
        application.setContractNumber("A-11731");
        application.setEmployment("Salaried");
        application.setPremiumInCent(500);
        application.setProduct("Active account");

        return application;
    }

    public static NewApplication yellowApplication() {
        NewApplication application = new NewApplication();
        application.setApplicant(new Person());
        application.getApplicant().setBirthday(getDate(1993, 9, 11));
        application.getApplicant().setName(NAME);
        application.getApplicant().setEmail(EMAIL);
        application.getApplicant().setGender(GENDER);
        //application.setScore(95);
        application.setCategory(Categorys.PREMIUM_PACKAGE.displayName());
        application.setEmployment(Employment.SELF_EMPLOYED.displayName());
        application.setPriceIndicationInCent(32000);
        application.setCorporation("Camunbankia");
        application.setProduct("Active account");
        return application;
    }

    public static NewApplication redApplication() {
        NewApplication application = new NewApplication();
        application.setApplicant(new Person());
        application.getApplicant().setBirthday(getDate(2005, 9, 11));
        application.getApplicant().setName(NAME);
        application.getApplicant().setEmail(EMAIL);
        application.getApplicant().setGender(GENDER);
        //application.setScore(82);
        application.setCategory(Categorys.PREMIUM_PACKAGE.displayName());
        application.setEmployment(Employment.SELF_EMPLOYED.displayName());
        application.setPriceIndicationInCent(32000);
        application.setCorporation("Camunbankia");
        application.setProduct("Active account");
        return application;
    }
    private static Date getDate(int year, int month, int day) {
        return Date.from(LocalDate.of(year, month, day).atStartOfDay(ZoneId.systemDefault()).toInstant());
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
}