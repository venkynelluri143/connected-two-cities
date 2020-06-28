package com.walmart.gga.controller;

import javax.servlet.Filter;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.walmart.gai.GetAssociateIdentifier;
import com.walmart.gai.model.AssocIdentifier;
import com.walmart.gai.model.AssocIdentifierRequest;
import com.walmart.gai.util.Constants;


@RunWith(MockitoJUnitRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = AppConfig.class)
@WebAppConfiguration
//@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigWebContextLoader.class)
@TestPropertySource(locations = "classpath:application-dev.properties")
@ContextConfiguration(classes = GetAssociateIdentifier.class)
public class AssociateIdentifierControllerTest{
	private static final Logger LOGGER = LoggerFactory.getLogger(AssociateIdentifierControllerTest.class);
	private static final String APPLN_CHARSET = "application/json;charset=UTF-8";
	
	@Autowired
    private WebApplicationContext wac;
	
    private MockMvc mockMvc;
    
    @Autowired
    private Filter springSecurityFilterChain;
    
    //@Before
	public void setup() {
		try {
			this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).defaultRequest(post("/")).addFilter(springSecurityFilterChain).build();
		} catch (Exception e) {
			LOGGER.error("Exception test - testYesNo" + e);
		}
	}
    
    @Test
    public void contextLoads() {
    }
    
    @Test
    public void validateTestInfo(){
    	System.out.println("Test Method");
    }
    
    //@Test
    public void validateGetAssociateIdentifier(){
    	try{
    		LOGGER.info(
					"==================================Entering into validateGetAssocIdentifier================================");
    		AssocIdentifierRequest request = new AssocIdentifierRequest();
    		AssocIdentifier assocIdentifier = new AssocIdentifier();
    		assocIdentifier.setAssociateId("");
    		assocIdentifier.setIdType(Constants.WALMART_IDENTIFICATION_NUM);
    		request.setAssocIdentifier(assocIdentifier);
    		request.setCountryCode(Constants.COUNTRYCODE_US);
    		
    		URI url = new URI("/GetAssocIdentifier/assocIdentifier/associate");
    		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac) .build();
    		
			this.mockMvc.perform(
					post(url).content(convertObjectToJsonArray(request)).contentType(MediaType.parseMediaType(APPLN_CHARSET)))
					.andExpect(status().isOk());
    		
    		
    		LOGGER.info(
					"==================================Exiting from validateGetAssocIdentifier=================================");
    		
    	}catch(Exception e){
    		LOGGER.info("Exception is", e);
    	}
    }
    
    private String convertObjectToJsonArray(AssocIdentifierRequest request) throws JsonProcessingException {
    	ObjectMapper mapper = new ObjectMapper();
    	String inputData = null;
    	inputData = mapper.writeValueAsString(request);
    	LOGGER.info(inputData);
    	return inputData;
        }
    
    
}
