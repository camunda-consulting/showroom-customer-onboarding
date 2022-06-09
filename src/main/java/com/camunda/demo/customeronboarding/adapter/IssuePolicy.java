package com.camunda.demo.customeronboarding.adapter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camunda.demo.customeronboarding.model.NewApplication;
import com.camunda.demo.customeronboarding.service.ContractService;

import fr.opensagres.xdocreport.core.XDocReportException;
import io.camunda.zeebe.spring.client.annotation.ZeebeVariablesAsType;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;

@Component
public class IssuePolicy {

    private final static Logger LOG = LoggerFactory.getLogger(IssuePolicy.class);

    @Autowired
    private ContractService contractService;

    @ZeebeWorker(type = "issuePolicy", autoComplete = true)
    public NewApplication issuePolicy(@ZeebeVariablesAsType NewApplication newApplication) throws IOException, XDocReportException {

        LOG.info("Invoking issuePolicy for application : " + newApplication.getApplicationNumber());
        newApplication.setContractNumber(String.valueOf(System.currentTimeMillis()));

        String contract = contractService.generateContract(newApplication);
        String driveId = contractService.storeInDrive(contract);
        newApplication.setContractDriveId(driveId);
        contractService.deleteContract(contract);

        return newApplication;
    }

}
