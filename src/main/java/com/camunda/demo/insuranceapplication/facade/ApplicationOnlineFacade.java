package com.camunda.demo.insuranceapplication.facade;

import static org.camunda.spin.Spin.JSON;

import java.io.UnsupportedEncodingException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.engine.impl.digest._apacheCommonsCodec.Base64;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;
import org.camunda.bpm.engine.variable.value.FileValue;

import com.camunda.demo.insuranceapplication.ProcessConstants;
import com.camunda.demo.insuranceapplication.model.NewApplication;

@Path("/")
public class ApplicationOnlineFacade {

  @POST
  @Path("new-application/{lang}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.TEXT_HTML)
  public Response submitNewApplication(NewApplication application, @Context HttpHeaders headers, @PathParam("lang") String lang) {
    String processInstanceKey;
    if ("en".equals(lang)) {
      processInstanceKey = ProcessConstants.PROCESS_KEY_insurance_application_en;
    } else if ("de".equals(lang)) {
      processInstanceKey = ProcessConstants.PROCESS_KEY_insurance_application_de;
    } else {
      throw new RuntimeException("Unsupported language requested.");
    }
    String referer = headers.getHeaderString("Referer");
    System.out.println("ich bin der refererer: " + referer);
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
            );
    
    return Response.status(200).entity(processInstance.getBusinessKey()).build();
  }

  @POST
  @Path("document/{number}")
  @Consumes(MediaType.APPLICATION_JSON)
  public void submitDocument(@PathParam("number") String number, VariableValueDto documentVariable) throws UnsupportedEncodingException {

    FileValue document = Variables.fileValue((String) documentVariable.getValueInfo().get("filename"))
        .file(Base64.decodeBase64((String) documentVariable.getValue())) // see
                                                                         // FileValueTypeImpl.createValue
        .mimeType((String) documentVariable.getValueInfo().get("mimeType")).create();

    BpmPlatform.getDefaultProcessEngine().getRuntimeService().createMessageCorrelation(ProcessConstants.MESSAGE_documentReceived)
        .processInstanceVariableEquals(ProcessConstants.VAR_NAME_documentReferenceId, number).setVariable(ProcessConstants.VAR_NAME_document, document)
        .correlateWithResult();
  }

}
