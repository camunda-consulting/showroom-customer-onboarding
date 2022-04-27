package com.camunda.demo.customeronboarding;

import static io.camunda.zeebe.process.test.assertions.BpmnAssert.assertThat;
import static io.camunda.zeebe.spring.test.ZeebeTestThreadSupport.waitForProcessInstanceHasPassedElement;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.camunda.demo.customeronboarding.facade.ApplicationOnlineFacade;
import com.camunda.demo.customeronboarding.model.NewApplication;
import com.camunda.demo.customeronboarding.model.Person;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import io.camunda.zeebe.process.test.api.ZeebeTestEngine;
import io.camunda.zeebe.spring.test.ZeebeSpringTest;

@SpringBootTest
@ZeebeSpringTest
public class NewApplicationProcessTest {
    @Autowired
    private ZeebeClient client;

    @Autowired
    private ZeebeTestEngine engine;

    private Date getDate(int year, int month, int day) {
        return Date.from(LocalDate.of(year, month, day).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @Test
    public void testHappyPath() throws Exception {
        NewApplication application = greenApplication();

        ProcessInstanceEvent processInstance = client.newCreateInstanceCommand()
                .bpmnProcessId(ProcessConstants.PROCESS_KEY_customer_onboarding_en).latestVersion()
                .variables(application).send().join();

        engine.waitForIdleState(Duration.ofSeconds(1));

        assertThat(processInstance).isStarted();

        waitForProcessInstanceHasPassedElement(processInstance, "ServiceTask_0tixwo5");

        assertThat(processInstance).hasVariableWithValue("score", 97);

        waitForProcessInstanceHasPassedElement(processInstance, "BusinessRuleTask_CheckApplicationAutomatically");

        assertThat(processInstance).hasVariableWithValue("riskLevels", new String[]{"green"});

    }

    public NewApplication greenApplication() {
        NewApplication application = new NewApplication();
        application.setApplicant(new Person());
        application.getApplicant().setAge(35);
        application.getApplicant().setBirthday(getDate(1994, 9, 11));
        application.getApplicant().setName("John Doe");
        application.setUiBaseUrl("http://localhost:8080/camunda/online/banking/");
        application.setCorporation("Camunbankia");
        application.setCategory("Standard Package");
        application.setContractNumber("A-11731");
        application.setEmployment("Salaried");
        application.setPremiumInCent(500);
        application.setProduct("Active account");

        return application;
    }
}
