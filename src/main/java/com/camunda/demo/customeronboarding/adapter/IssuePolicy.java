package com.camunda.demo.customeronboarding.adapter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.camunda.demo.customeronboarding.model.NewApplication;
import com.camunda.demo.customeronboarding.utils.DocxPdfUtils;
import com.camunda.demo.customeronboarding.utils.DriveUtils;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

import fr.opensagres.xdocreport.core.XDocReportException;
import io.camunda.zeebe.spring.client.annotation.ZeebeVariablesAsType;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;

@Component
public class IssuePolicy {
    
  private final static Logger LOG = LoggerFactory.getLogger(IssuePolicy.class);

  @ZeebeWorker(type = "issuePolicy", autoComplete = true)
  public NewApplication issuePolicy(@ZeebeVariablesAsType NewApplication newApplication) throws IOException, XDocReportException {

    newApplication.setContractNumber(String.valueOf(System.currentTimeMillis()));

    LOG.info("Invoking issuePolicy for application : " + newApplication.getApplicationNumber());
    String target = "Contract_"+newApplication.getContractNumber()+".pdf";
   

    DocxPdfUtils.generatePdf("ContractTemplate.docx", target, Map.of("application", newApplication));

    Drive drive = DriveUtils.drive();
    
    // upload to Google Drive
    File fileMetadata = new File();
    fileMetadata.setName(target);
    fileMetadata.setMimeType("application/pdf");

    InputStreamContent mediaContent = new InputStreamContent("application/pdf", new FileInputStream(target));
    File file = drive.files()
        .create(fileMetadata, mediaContent)
        .setFields("id")
        .execute();
    newApplication.setContractDriveId(file.getId());
    LOG.info("Archive contract in Google Drive with id: " + file.getId());

    try {
        Files.delete(Paths.get(target));
    } catch (IOException e) {
        LOG.error("Error deleting file", e);
    }
    
    return newApplication;
  }

}
