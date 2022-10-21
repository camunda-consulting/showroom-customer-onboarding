package com.camunda.demo.customeronboarding.model;

import java.util.Calendar;
import java.util.Date;

public class Person {

  private String name;
  private String email;
  private Date birthday;
  // TODO: EN/DE switch
  private String gender;
  private int age;
  
  public int calculateAge(Date atDate) {
    Calendar dob = Calendar.getInstance();  
    dob.setTime(birthday);  
    Calendar today = Calendar.getInstance(); 
    today.setTime(atDate);
    
    int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);  
    if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
      age--;  
    } else if (today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)
        && today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
      age--;  
    }
    
    return age;
  }
  
  public Date getBirthday() {
    return birthday;
  }
  public void setBirthday(Date birthday) {
    this.birthday = birthday;
     setAge(calculateAge(new Date()));
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
