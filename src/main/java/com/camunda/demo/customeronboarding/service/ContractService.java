package com.camunda.demo.customeronboarding.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.camunda.demo.customeronboarding.model.NewApplication;
import com.camunda.demo.customeronboarding.utils.DocxPdfUtils;
import com.camunda.demo.customeronboarding.utils.DriveUtils;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

import fr.opensagres.xdocreport.core.XDocReportException;

@Service
public class ContractService {
    
    private final static Logger LOG = LoggerFactory.getLogger(ContractService.class);
    
    public String generateContract(NewApplication application) throws IOException, XDocReportException {
        String target = "Contract_"+application.getContractNumber()+".pdf";
        

        DocxPdfUtils.generatePdf("ContractTemplate.docx", target, Map.of("application", application));
        return target;
    }
    
    public String storeInDrive(String filename) throws IOException {
        Drive drive = DriveUtils.drive();
        
        // upload to Google Drive
        File fileMetadata = new File();
        fileMetadata.setName(filename);
        fileMetadata.setMimeType("application/pdf");

        InputStreamContent mediaContent = new InputStreamContent("application/pdf", new FileInputStream(filename));
        File file = drive.files()
            .create(fileMetadata, mediaContent)
            .setFields("id")
            .execute();
        LOG.info("Archive contract in Google Drive with id: " + file.getId());
        return file.getId();
    }
    
    public boolean deleteContract(String filename) {
        try {
            Files.delete(Paths.get(filename));
            return true;
        } catch (IOException e) {
            LOG.error("Error deleting file", e);
            return false;
        }
    }
    
}
