package com.camunda.demo.customeronboarding;

import static org.camunda.bpm.engine.test.assertions.bpmn.AbstractAssertions.init;

import org.camunda.bpm.application.AbstractProcessApplication;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@Deployment(resources = { "static/bpmn/customer_onboarding_en.bpmn", "static/bpmn/document_request_en.bpmn", "static/bpmn/risk_check_en.dmn", "static/bpmn/customer_onboarding_de.bpmn",
    "static/bpmn/document_request_de.bpmn", "static/bpmn/risk_check_de.dmn" })
@SpringBootTest(webEnvironment=WebEnvironment.NONE)
@RunWith(SpringRunner.class)
@Ignore
public class SpringBootProcessTest {
  
  static final Logger logger = LoggerFactory.getLogger(SpringBootProcessTest.class);

  @MockBean
  private StartConfiguration sim;
  
  @Autowired
  private ProcessEngine processEngine;
  @Autowired
  private AbstractProcessApplication processApp;
  
  @Before
  public void setup() throws Exception {    
    init(processEngine);
    MockitoAnnotations.initMocks(this);
    (new DeploymentService(processEngine, processApp)).deployAllCurrent();
  }
  
}
