package com.camunda.demo.customeronboarding.facade;

import static org.camunda.spin.Spin.JSON;

import java.io.UnsupportedEncodingException;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.engine.impl.digest._apacheCommonsCodec.Base64;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;
import org.camunda.bpm.engine.variable.value.FileValue;
import org.camunda.bpm.spring.boot.starter.webapp.CamundaBpmWebappInitializer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camunda.demo.customeronboarding.ProcessConstants;
import com.camunda.demo.customeronboarding.model.NewApplication;

@RestController
@RequestMapping("API")
public class ApplicationOnlineFacade {

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

    ProcessInstance processInstance = BpmPlatform.getDefaultProcessEngine().getRuntimeService().startProcessInstanceByKey(processInstanceKey, application.getApplicationNumber(),
        Variables.createVariables() //
            .putValueTyped(ProcessConstants.VAR_NAME_application,
                Variables.objectValue(application).serializationDataFormat(SerializationDataFormats.JSON).create()) //
            .putValue(ProcessConstants.VAR_NAME_documents, JSON("{}"))//
            .putValue(ProcessConstants.VAR_NAME_uiBaseUrl, uiBaseUrl)//
            .putValue(ProcessConstants.VAR_NAME_applicationNumber, application.getApplicationNumber())//
            .putValue(ProcessConstants.VAR_NAME_applicantName, application.getApplicant().getName())//
			.putValue(ProcessConstants.VAR_NAME_rpa, application.getRpa())
            );
    
    return processInstance.getBusinessKey();
  }

  @PostMapping(path="/document/{number}")
  public void submitDocument(@PathVariable("number") String number, @RequestBody VariableValueDto documentVariable) throws UnsupportedEncodingException {

    FileValue document = Variables.fileValue((String) documentVariable.getValueInfo().get("filename"))
        .file(Base64.decodeBase64((String) documentVariable.getValue())) // see
                                                                         // FileValueTypeImpl.createValue
        .mimeType((String) documentVariable.getValueInfo().get("mimeType")).create();

    BpmPlatform.getDefaultProcessEngine().getRuntimeService().createMessageCorrelation(ProcessConstants.MESSAGE_documentReceived)
        .processInstanceVariableEquals(ProcessConstants.VAR_NAME_documentReferenceId, number).setVariable(ProcessConstants.VAR_NAME_document, document)
        .correlateWithResult();
  }

}
