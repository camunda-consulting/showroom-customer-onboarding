package com.camunda.demo.customeronboarding;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.ZoneId;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.dmn.engine.DmnDecisionResult;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.variable.Variables;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.camunda.demo.customeronboarding.model.NewApplication;

public class DmnRuleTests extends SpringBootProcessTest {

  @Autowired
  private ProcessEngine processEngine;

  @Test
  public void shouldReturnGreen() {
    testGreenRule(ProcessConstants.DECISION_KEY_checkRisk_en);
    testGreenRule(ProcessConstants.DECISION_KEY_checkRisk_de);
  }

  @Test
  public void shouldReturnYellow() {
    testYellowRules(ProcessConstants.DECISION_KEY_checkRisk_en);
    testYellowRules(ProcessConstants.DECISION_KEY_checkRisk_de);
  }

  @Test
  public void shouldReturnRed() {
    testRedRules(ProcessConstants.DECISION_KEY_checkRisk_en);
    testRedRules(ProcessConstants.DECISION_KEY_checkRisk_de);
  }

  private void testRedRules(String dmnDecisionKey) {
    testRedRuleFirst(dmnDecisionKey);
    testRedRuleSecond(dmnDecisionKey);
  }

  private void testRedRuleFirst(String dmnDecisionKey) {
    boolean german = isGerman(dmnDecisionKey);
    NewApplication application = DemoData.ruleRedFirst(german);
    DmnDecisionResult result = getDmnResult(dmnDecisionKey, application);

    String expectedRisk = german ? "Fehlendes Einkommen" : "Lack Of Income";
    String expectedLevel = german ? "rot" : "red";

    assertThat(result).hasSize(1);
    assertThat(result.getResultList().get(0)).containsEntry("risk", expectedRisk).containsEntry("riskLevel",
        expectedLevel);
  }

  private void testRedRuleSecond(String dmnDecisionKey) {
    boolean german = isGerman(dmnDecisionKey);
    NewApplication application = DemoData.ruleRedSecond(german);
    DmnDecisionResult result = getDmnResult(dmnDecisionKey, application);

    String expectedRisk = german ? "Zahlung unwahrscheinlich" : "Won't Pay In Time";
    String expectedLevel = german ? "rot" : "red";

    assertThat(result).hasSize(1);
    assertThat(result.getResultList().get(0)).containsEntry("risk", expectedRisk).containsEntry("riskLevel",
        expectedLevel);
  }

  private void testYellowRules(String dmnDecisionKey) {
    testYellowRuleFirst(dmnDecisionKey);
    testYellowRuleSecond(dmnDecisionKey);
    testYellowRuleThird(dmnDecisionKey);
    testYellowRuleSecondAndThird(dmnDecisionKey);
  }

  private void testYellowRuleFirst(String dmnDecisionKey) {
    boolean german = isGerman(dmnDecisionKey);
    NewApplication application = DemoData.ruleYellowFirst(german);
    DmnDecisionResult result = getDmnResult(dmnDecisionKey, application);

    String expectedRisk = german ? "Ggf. zu geringes Einkommen" : "Too Low Income";
    String expectedLevel = german ? "gelb" : "yellow";

    assertThat(result).hasSize(1);
    assertThat(result.getResultList().get(0)).containsEntry("risk", expectedRisk).containsEntry("riskLevel",
        expectedLevel);
  }

  private void testYellowRuleSecond(String dmnDecisionKey) {
    boolean german = isGerman(dmnDecisionKey);

    NewApplication application = DemoData.ruleYellowSecond(german);
    DmnDecisionResult result = getDmnResult(dmnDecisionKey, application);

    String expectedRisk = german ? "Nicht vertrauenswürdig" : "Not Trustworthy";
    String expectedLevel = german ? "gelb" : "yellow";

    assertThat(result).hasSize(1);
    assertThat(result.getResultList().get(0)).containsEntry("risk", expectedRisk).containsEntry("riskLevel",
        expectedLevel);
  }

  private void testYellowRuleThird(String dmnDecisionKey) {
    boolean german = isGerman(dmnDecisionKey);

    NewApplication application = DemoData.ruleYellowThird(german);
    DmnDecisionResult result = getDmnResult(dmnDecisionKey, application);

    String expectedRisk = german ? "Unregelmäßiges und ggf. zu geringes Einkommen"
        : "Irregular And Possibly Lack Of Income";
    String expectedLevel = german ? "gelb" : "yellow";

    assertThat(result).hasSize(1);
    assertThat(result.getResultList().get(0)).containsEntry("risk", expectedRisk).containsEntry("riskLevel",
        expectedLevel);
  }

  private void testYellowRuleSecondAndThird(String dmnDecisionKey) {
    boolean german = isGerman(dmnDecisionKey);

    NewApplication application = DemoData.ruleYellowSecondAndThird(german);
    DmnDecisionResult result = getDmnResult(dmnDecisionKey, application);

    String firstExpectedRisk = german ? "Unregelmäßiges und ggf. zu geringes Einkommen"
        : "Irregular And Possibly Lack Of Income";
    String secondExpectedRisk = german ? "Nicht vertrauenswürdig" : "Not Trustworthy";
    String expectedLevel = german ? "gelb" : "yellow";

    assertThat(result).hasSize(2);
    assertThat(result.getResultList().get(0)).containsEntry("risk", firstExpectedRisk).containsEntry("riskLevel",
        expectedLevel);
    assertThat(result.getResultList().get(1)).containsEntry("risk", secondExpectedRisk).containsEntry("riskLevel",
        expectedLevel);
  }

  private void testGreenRule(String dmnDecisionKey) {
    NewApplication application = DemoData.green(isGerman(dmnDecisionKey));
    DmnDecisionResult result = getDmnResult(dmnDecisionKey, application);

    assertThat(result).isEmpty();
  }

  private DmnDecisionResult getDmnResult(String dmnDecisionKey, NewApplication application) {
    return processEngine.getDecisionService().evaluateDecisionByKey(dmnDecisionKey)
        .variables(dmnVariablesFromApplication(application)).evaluate();
  }

  private Map<String, Object> dmnVariablesFromApplication(NewApplication application) {
    int yearLastDigit = application.getApplicant().getBirthday().toInstant().atZone(ZoneId.systemDefault())
        .toLocalDate().getYear() % 10;
    int score = 97;
    if (yearLastDigit == 3) {
      score = 93;
    } else if (yearLastDigit == 5) {
      score = 82;
    }

    return Variables.putValue("application", application).putValue("score", score);
  }

}
