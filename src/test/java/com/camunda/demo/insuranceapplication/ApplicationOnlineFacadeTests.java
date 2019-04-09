package com.camunda.demo.insuranceapplication;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.HttpHeaders;
import org.jboss.resteasy.core.Headers;
import org.junit.Ignore;
import org.junit.Test;

import com.camunda.demo.insuranceapplication.DemoData.Categorys;
import com.camunda.demo.insuranceapplication.DemoData.Employment;
import com.camunda.demo.insuranceapplication.facade.ApplicationOnlineFacade;
import com.camunda.demo.insuranceapplication.model.NewApplication;

@Ignore
public class ApplicationOnlineFacadeTests {
	
	ApplicationOnlineFacade applicationOnlineFacade = new ApplicationOnlineFacade();
	
	@Test
	public void submitNewApplicationTest() {
		NewApplication application = new NewApplication();
		application.setCategory(Categorys.BASISPAKET.displayName());
		application.setEmployment(Employment.FREELANCER.displayName());
		application.setIncome(354584);
		application.setPremium("34345");
		application.setPremiumInCent(566464646);
		
		MultivaluedMap<String, String> requestHeaders = new MultivaluedHashMap<>();
		HttpHeaders headers = new  org.jboss.resteasy.specimpl.ResteasyHttpHeaders(requestHeaders);
		applicationOnlineFacade.submitNewApplication(application, headers, "en");
	}

}
