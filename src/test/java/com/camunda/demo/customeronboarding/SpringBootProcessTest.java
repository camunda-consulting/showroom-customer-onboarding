package com.camunda.demo.customeronboarding;

import static org.camunda.bpm.engine.test.assertions.bpmn.AbstractAssertions.init;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.List;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.camunda.demo.customeronboarding.adapter.SendEmailService;

@Deployment(resources = { "static/bpmn/customer_onboarding_en.bpmn", "static/bpmn/document_request_en.bpmn", "static/bpmn/risk_check_en.dmn", "static/bpmn/customer_onboarding_de.bpmn",
    "static/bpmn/document_request_de.bpmn", "static/bpmn/risk_check_de.dmn" })
@SpringBootTest(webEnvironment=WebEnvironment.NONE)
@RunWith(SpringRunner.class)
@Ignore
public class SpringBootProcessTest {
  
  static final Logger logger = LoggerFactory.getLogger(SpringBootProcessTest.class);
  
  @MockBean
  protected SendEmailService sendEmailService;
  @MockBean
  private StartConfiguration sim;
  
  @Autowired
  private ProcessEngine processEngine;
  
  @Before
  public void setup() throws Exception {    
    init(processEngine);
    MockitoAnnotations.initMocks(this);
    
    Mockito.doAnswer(invocation -> {
      logger.info("I'm mocking a mail to <" + invocation.getArguments()[0] + ">.");
      logger.debug("  Mock mail subject: " + invocation.getArguments()[2]);
      logger.debug("  Mock mail body: " + invocation.getArguments()[1]);
      return null;
    }).when(sendEmailService).sendEmail(anyString(), anyString(), anyString());
  }
  
}
