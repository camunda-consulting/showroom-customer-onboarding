package com.camunda.demo.customeronboarding.facade;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camunda.demo.customeronboarding.ProcessConstants;
import com.camunda.demo.customeronboarding.model.NewApplication;

import io.camunda.zeebe.client.ZeebeClient;

@RestController
@RequestMapping("API")
public class ApplicationOnlineFacade {

  @Autowired
  private ZeebeClient  client;

  @PostMapping(path="/new-application/{lang}", produces=MediaType.TEXT_HTML_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
  public String submitNewApplication(@RequestBody NewApplication application, @RequestHeader("referer") String referer, @PathVariable("lang") String lang) {
    String uiBaseUrl = referer.substring(0, referer.lastIndexOf('/')) + "/";
    application.setUiBaseUrl(uiBaseUrl);

    client
      .newCreateInstanceCommand()
      .bpmnProcessId(ProcessConstants.PROCESS_KEY)
      .latestVersion()
      .variables(application)
      .send()
      .join();

    return application.getApplicationNumber();
  }

  

  @PostMapping(path="/document/{number}")
  public void submitDocument(@PathVariable("number") String number, @RequestBody Object document) throws UnsupportedEncodingException {
//    TODO: Receive and store find, hand in message with reference into zeebe

//    client
//        .newPublishMessageCommand()
//        .messageName(ProcessConstants.MESSAGE_documentReceived)
//        .correlationKey(number)
//        .variables(Map.of(ProcessConstants.VAR_NAME_document, documentVariable.getValueInfo().get("filename")))
//        .send()
//        .join();
  }

}
