package com.camunda.demo.customeronboarding;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.ws.rs.client.Entity;

import org.camunda.bpm.engine.ProcessEngine;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.camunda.demo.customeronboarding.DemoData.Categorys;
import com.camunda.demo.customeronboarding.DemoData.Employment;
import com.camunda.demo.customeronboarding.facade.ApplicationOnlineFacade;
import com.camunda.demo.customeronboarding.model.NewApplication;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment=WebEnvironment.MOCK)
@Ignore
public class ApplicationOnlineFacadeTests extends SpringBootProcessTest {
	
    @Autowired
	ApplicationOnlineFacade applicationOnlineFacade;
	
	@Autowired
    private MockMvc mvc;
	
	@Test
	public void reallySubmitNewApplicationTest() throws Exception {
	  NewApplication application = new NewApplication();
      application.setCategory(Categorys.BASISPAKET.displayName());
      application.setEmployment(Employment.FREELANCER.displayName());
      application.setPremium("34345");
      application.setPremiumInCent(566464646);
      application.setProduct("even cooler product");
      
//	  MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/new-application/de")
//	                                                                        .contentType(MediaType.APPLICATION_JSON)
//	                                                                        .content(Entity.json(application).toString());

      MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("");
      
      mvc.perform(requestBuilder)
          .andExpect(status().is(200))
          .andDo(print());
	}
	
	@Test
	public void submitNewApplicationTest() {
		NewApplication application = new NewApplication();
		application.setCategory(Categorys.BASISPAKET.displayName());
		application.setEmployment(Employment.FREELANCER.displayName());
		application.setPremium("34345");
		application.setPremiumInCent(566464646);
		application.setProduct("even cooler product");
		
		applicationOnlineFacade.submitNewApplication(application, "me/or/you", "en");
	}

}
