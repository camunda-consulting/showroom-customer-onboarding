package com.camunda.demo.customeronboarding;

import static io.camunda.zeebe.process.test.assertions.BpmnAssert.assertThat;
import static io.camunda.zeebe.spring.test.ZeebeTestThreadSupport.waitForProcessInstanceHasPassedElement;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.camunda.demo.customeronboarding.model.NewApplication;
import com.camunda.demo.customeronboarding.service.ContractService;
import com.camunda.demo.customeronboarding.service.ScoringService;
import com.camunda.demo.customeronboarding.service.SendMailService;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import io.camunda.zeebe.process.test.api.ZeebeTestEngine;
import io.camunda.zeebe.spring.test.ZeebeSpringTest;

@SpringBootTest
@ZeebeSpringTest
public class NewApplicationProcessTest {
    
    private static final String CONTRACT_NAME = "contract.pdf";
    private static final String DRIVE_ID = "2";

    @Autowired
    private ZeebeClient client;

    @Autowired
    private ZeebeTestEngine engine;

    @MockBean
    private ScoringService scoringService;

    @MockBean
    private SendMailService sendMailService;

    @MockBean
    private ContractService contractService;
    
    @Test
    public void testGreenPath() throws Exception {
        
        NewApplication application = TestDataUtil.greenApplication();
        when(scoringService.getScore(application)).thenReturn(97);
        when(contractService.generateContract(application)).thenReturn(CONTRACT_NAME);
        when(contractService.deleteContract(CONTRACT_NAME)).thenReturn(true);
        when(contractService.storeInDrive(CONTRACT_NAME)).thenReturn(DRIVE_ID);
        doNothing().when(sendMailService).sendEmail(application);
        
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
        when(scoringService.getScore(application)).thenReturn(93);
        when(contractService.generateContract(application)).thenReturn(CONTRACT_NAME);
        when(contractService.deleteContract(CONTRACT_NAME)).thenReturn(true);
        when(contractService.storeInDrive(CONTRACT_NAME)).thenReturn(DRIVE_ID);
        doNothing().when(sendMailService).sendEmail(application);
        
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
        when(scoringService.getScore(application)).thenReturn(82);
        when(contractService.generateContract(application)).thenReturn(CONTRACT_NAME);
        when(contractService.deleteContract(CONTRACT_NAME)).thenReturn(true);
        when(contractService.storeInDrive(CONTRACT_NAME)).thenReturn(DRIVE_ID);
        doNothing().when(sendMailService).sendEmail(application);
        
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
