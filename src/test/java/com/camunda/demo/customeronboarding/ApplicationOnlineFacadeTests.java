package com.camunda.demo.customeronboarding;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import org.junit.Ignore;
import org.junit.Test;

import com.camunda.demo.customeronboarding.DemoData.Categorys;
import com.camunda.demo.customeronboarding.DemoData.Employment;
import com.camunda.demo.customeronboarding.facade.ApplicationOnlineFacade;
import com.camunda.demo.customeronboarding.model.NewApplication;

@Ignore
public class ApplicationOnlineFacadeTests {
	
	ApplicationOnlineFacade applicationOnlineFacade = new ApplicationOnlineFacade();
	
	@Test
	public void submitNewApplicationTest() {
		NewApplication application = new NewApplication();
		application.setCategory(Categorys.BASISPAKET.displayName());
		application.setEmployment(Employment.FREELANCER.displayName());
		application.setPremium("34345");
		application.setPremiumInCent(566464646);
		application.setProduct("even cooler product");
		
		MultivaluedMap<String, String> requestHeaders = new MultivaluedHashMap<>();
		HttpHeaders headers = new  org.jboss.resteasy.specimpl.ResteasyHttpHeaders(requestHeaders);
		applicationOnlineFacade.submitNewApplication(application, headers, "en");
	}

}
