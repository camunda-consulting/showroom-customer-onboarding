package com.camunda.demo.customeronboarding.facade;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.camunda.zeebe.spring.client.ZeebeClientLifecycle;

import com.camunda.demo.customeronboarding.ProcessConstants;
import com.camunda.demo.customeronboarding.model.NewApplication;

@RestController
@RequestMapping("API")
public class ApplicationOnlineFacade {

  @Autowired
  private ZeebeClientLifecycle client;

  @PostMapping(path="/new-application/{lang}", produces=MediaType.TEXT_HTML_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
  public String submitNewApplication(@RequestBody NewApplication application, @RequestHeader("referer") String referer, @PathVariable("lang") String lang) {
    String processInstanceKey;
    if ("en".equals(lang)) {
      processInstanceKey = ProcessConstants.PROCESS_KEY_customer_onboarding_en;
    } else if ("de".equals(lang)) {
      processInstanceKey = ProcessConstants.PROCESS_KEY_customer_onboarding_de;
    } else {
      throw new RuntimeException("Unsupported language requested.");
    }
    String uiBaseUrl = referer.substring(0, referer.lastIndexOf('/')) + "/";
    
    // we start by using the shown price - user can change it on decision form
    application.setPremiumInCent(application.getPriceIndicationInCent());

    Map<String, Object> variables = new HashMap<>();
    variables.put(ProcessConstants.VAR_NAME_uiBaseUrl, uiBaseUrl);
    variables.put(ProcessConstants.VAR_NAME_applicationNumber, application.getApplicationNumber());
    variables.put(ProcessConstants.VAR_NAME_applicantName, application.getApplicant().getName());
    variables.put(ProcessConstants.VAR_NAME_application, application);
    variables.put(ProcessConstants.VAR_NAME_documents, "{}");

    client
      .newCreateInstanceCommand()
      .bpmnProcessId(processInstanceKey)
      .latestVersion()
      .variables(variables)
      .send()
      .join();

    return application.getApplicationNumber();
  }

  

  @PostMapping(path="/document/{number}")
  public void submitDocument(@PathVariable("number") String number, @RequestBody VariableValueDto documentVariable) throws UnsupportedEncodingException {
    client
        .newPublishMessageCommand()
        .messageName(ProcessConstants.MESSAGE_documentReceived)
        .correlationKey(number)
        .variables(Map.of(ProcessConstants.VAR_NAME_document, documentVariable.getValueInfo().get("filename")))
        .send()
        .join();

/*
    FileValue document = Variables.fileValue((String) documentVariable.getValueInfo().get("filename"))
        .file(Base64.decodeBase64((String) documentVariable.getValue())) // see
                                                                         // FileValueTypeImpl.createValue
        .mimeType((String) documentVariable.getValueInfo().get("mimeType")).create();
*/
  }

}
