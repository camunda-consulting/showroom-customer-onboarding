package com.camunda.demo.customeronboarding;

import static io.camunda.zeebe.process.test.assertions.BpmnAssert.assertThat;
import static io.camunda.zeebe.spring.test.ZeebeTestThreadSupport.*;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.camunda.demo.customeronboarding.model.NewApplication;

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

    @Test
    public void testGreenPath() throws Exception {
        NewApplication application = TestDataUtil.greenApplication();

        ProcessInstanceEvent processInstance = client.newCreateInstanceCommand()
                .bpmnProcessId(ProcessConstants.PROCESS_KEY).latestVersion()
                .variables(application).send().join();

        engine.waitForIdleState(Duration.ofSeconds(5));

        assertThat(processInstance).isStarted();

        waitForProcessInstanceHasPassedElement(processInstance, "ServiceTask_0tixwo5");

        assertThat(processInstance).hasVariableWithValue("score", 97);

        waitForProcessInstanceHasPassedElement(processInstance, "BusinessRuleTask_CheckApplicationAutomatically");

        waitForProcessInstanceHasPassedElement(processInstance, "ServiceTask_DeliverPolicy");
    }
    
    @Test
    public void testYellowPath() throws Exception {
        NewApplication application = TestDataUtil.yellowApplication();

        ProcessInstanceEvent processInstance = client.newCreateInstanceCommand()
                .bpmnProcessId(ProcessConstants.PROCESS_KEY).latestVersion()
                .variables(application).send().join();

        engine.waitForIdleState(Duration.ofSeconds(5));

        assertThat(processInstance).isStarted();

        waitForProcessInstanceHasPassedElement(processInstance, "ServiceTask_0tixwo5");

        assertThat(processInstance).hasVariableWithValue("score", 93);

        waitForProcessInstanceHasPassedElement(processInstance, "BusinessRuleTask_CheckApplicationAutomatically");
        
        assertThat(processInstance).hasVariableWithValue("riskLevels", new String[] {"yellow", "yellow"});

    }
    

    
    @Test
    public void testRedPath() throws Exception {
        NewApplication application = TestDataUtil.redApplication();

        ProcessInstanceEvent processInstance = client.newCreateInstanceCommand()
                .bpmnProcessId(ProcessConstants.PROCESS_KEY).latestVersion()
                .variables(application).send().join();

        engine.waitForIdleState(Duration.ofSeconds(5));

        assertThat(processInstance).isStarted();

        waitForProcessInstanceHasPassedElement(processInstance, "ServiceTask_0tixwo5");

        assertThat(processInstance).hasVariableWithValue("score", 82);

        waitForProcessInstanceHasPassedElement(processInstance, "BusinessRuleTask_CheckApplicationAutomatically");

        waitForProcessInstanceHasPassedElement(processInstance, "ServiceTask_RejectPolicy");
    }

}
