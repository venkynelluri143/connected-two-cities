package com.walmart.gga.service;

import javax.servlet.Filter;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.walmart.gai.GetAssociateIdentifier;
import com.walmart.gai.globalfilter.GlobalFilter;
import com.walmart.gai.model.AssocIdentifier;
import com.walmart.gai.model.AssocIdentifierRequest;
import com.walmart.gai.util.Constants;


@ActiveProfiles("dev")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GetAssociateIdentifier.class)
@WebAppConfiguration
public class AssociateIdentifierControllerTest{
	private static final Logger LOGGER = LoggerFactory.getLogger(AssociateIdentifierControllerTest.class);
	private static final String APPLN_CHARSET = "application/json;charset=UTF-8";
	
	@Autowired
    private WebApplicationContext wac;
	
    private MockMvc mockMvc;
    
    @Autowired
    private Filter springSecurityFilterChain;
    
    @Autowired
    private GlobalFilter globalFilter;
    
    private String userName = null; 
    private String credential = null; 
    private String incUserCred = null;
    
    @Before
	public void setup() {
		try {
			this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
					.addFilter(springSecurityFilterChain).build();
			userName = globalFilter.getPropValues(Constants.SVCUSERNAME);
			credential = globalFilter.getPropValues(Constants.SVCUSERCRED);
			incUserCred = globalFilter.getPropValues(Constants.SVCUSERCREDINCO);
		} catch (Exception e) {
			LOGGER.error("Exception test - testYesNo" + e);
		}
	}
    
    @Test
    public void validateGetAssociateIdentifier(){
    	try{
    		LOGGER.info("==================================Entering into validateGetAssocIdentifier================================");
    		AssocIdentifierRequest request = new AssocIdentifierRequest();
    		AssocIdentifier assocIdentifier = new AssocIdentifier();
    		assocIdentifier.setAssociateId("224222714");
    		assocIdentifier.setIdType(Constants.WALMART_IDENTIFICATION_NUM);
    		request.setAssocIdentifier(assocIdentifier);
    		request.setCountryCode(Constants.COUNTRYCODE_US);
    		
    		URI url = new URI("/assocIdentifier/associate");
    		
			this.mockMvc.perform(
					post(url).with(httpBasic(userName, credential)).content(convertObjectToJsonArray(request)).contentType(MediaType.parseMediaType(APPLN_CHARSET)))
					.andExpect(status().isOk());
    		
    		
    		LOGGER.info("==================================Exiting from validateGetAssocIdentifier=================================");
    		
    	}catch(Exception e){
    		LOGGER.info("Exception is", e);
    	}
    }
    
    @Test
    public void validateGetAssociateIdentifierBadRequest(){
    	try{
    		LOGGER.info("==================================Entering into validateGetAssocIdentifier================================");
    		AssocIdentifierRequest request = new AssocIdentifierRequest();
    		AssocIdentifier assocIdentifier = new AssocIdentifier();
    		assocIdentifier.setAssociateId(null);
    		assocIdentifier.setIdType(null);
    		request.setAssocIdentifier(assocIdentifier);
    		request.setCountryCode(null);
    		
    		URI url = new URI("/assocIdentifier/associate");
    		
			this.mockMvc.perform(
					post(url).with(httpBasic(userName, credential)).content(convertObjectToJsonArray(request)).contentType(MediaType.parseMediaType(APPLN_CHARSET)))
					.andExpect(status().isBadRequest());
    		
    		
    		LOGGER.info("==================================Exiting from validateGetAssocIdentifier=================================");
    		
    	}catch(Exception e){
    		LOGGER.info("Exception is", e);
    	}
    }
    
    @Test
    public void validateGetAssociateIdentifierInvalidRequest(){
    	try{
    		LOGGER.info("==================================Entering into validateGetAssocIdentifier================================");
    		AssocIdentifierRequest request = new AssocIdentifierRequest();
    		AssocIdentifier assocIdentifier = new AssocIdentifier();
    		assocIdentifier.setAssociateId(null);
    		assocIdentifier.setIdType("WININ");
    		request.setAssocIdentifier(assocIdentifier);
    		request.setCountryCode(null);
    		
    		URI url = new URI("/assocIdentifier/associate");
    		
			this.mockMvc.perform(
					post(url).with(httpBasic(userName, credential)).content(convertObjectToJsonArray(request)).contentType(MediaType.parseMediaType(APPLN_CHARSET)))
					.andExpect(status().isBadRequest());
    		
    		
    		LOGGER.info("==================================Exiting from validateGetAssocIdentifier=================================");
    		
    	}catch(Exception e){
    		LOGGER.info("Exception is", e);
    	}
    }
    
    @Test
    public void validateGetAssociateIdentifierBindException(){
    	try{
    		LOGGER.info("==================================Entering into validateGetAssocIdentifier================================");
    		AssocIdentifierRequest request = new AssocIdentifierRequest();
    		AssocIdentifier assocIdentifier = new AssocIdentifier();
    		assocIdentifier.setAssociateId("1234567890");
    		assocIdentifier.setIdType("WIN");
    		request.setAssocIdentifier(assocIdentifier);
    		request.setCountryCode(null);
    		
    		URI url = new URI("/assocIdentifier/associate");
    		
			this.mockMvc.perform(
					post(url).with(httpBasic(userName, credential)).content(convertObjectToJsonArray(request)).contentType(MediaType.parseMediaType(APPLN_CHARSET)))
					.andExpect(status().isBadRequest());
    		
    		
    		LOGGER.info("==================================Exiting from validateGetAssocIdentifier=================================");
    		
    	}catch(Exception e){
    		LOGGER.info("Exception is", e);
    	}
    }
    
    @Test
    public void validateGetAssociateIdentifierBadException(){
    	try{
    		LOGGER.info("==================================Entering into validateGetAssocIdentifier================================");
    		AssocIdentifierRequest request = new AssocIdentifierRequest();
    		AssocIdentifier assocIdentifier = null;
    		request.setAssocIdentifier(assocIdentifier);
    		request.setCountryCode(null);
    		
    		URI url = new URI("/assocIdentifier/associate");
    		
			this.mockMvc.perform(
					post(url).with(httpBasic(userName, credential)).content(convertObjectToJsonArray(request)).contentType(MediaType.parseMediaType(APPLN_CHARSET)))
					.andExpect(status().isBadRequest());
    		
    		
    		LOGGER.info("==================================Exiting from validateGetAssocIdentifier=================================");
    		
    	}catch(Exception e){
    		LOGGER.info("Exception is", e);
    	}
    }
    
    @Test
    public void validateGetAssociateIdentifierHealthCheck(){
    	try{
    		LOGGER.info("==================================Entering into validateGetAssocIdentifier================================");
    		URI url = new URI("/assocIdentifier/healthCheck");
    		
			this.mockMvc.perform(
					get(url).with(httpBasic(userName, credential)).contentType(MediaType.parseMediaType(APPLN_CHARSET)))
					.andExpect(status().isOk());
    		
    		
    		LOGGER.info("==================================Exiting from validateGetAssocIdentifier=================================");
    		
    	}catch(Exception e){
    		LOGGER.info("Exception is", e);
    	}
    }
    
    @Test
    public void validateGetAssociateIdentifierInternational(){
    	try{
    		LOGGER.info("==================================Entering into validateGetAssocIdentifier================================");
    		AssocIdentifierRequest request = new AssocIdentifierRequest();
    		AssocIdentifier assocIdentifier = new AssocIdentifier();
    		assocIdentifier.setAssociateId("226669310");
    		assocIdentifier.setIdType(Constants.WALMART_IDENTIFICATION_NUM);
    		request.setAssocIdentifier(assocIdentifier);
    		request.setCountryCode("MX");
    		
    		URI url = new URI("/assocIdentifier/associate");
    		
			this.mockMvc.perform(
					post(url).with(httpBasic(userName, credential)).content(convertObjectToJsonArray(request)).contentType(MediaType.parseMediaType(APPLN_CHARSET)))
					.andExpect(status().isOk());
    		
    		
    		LOGGER.info("==================================Exiting from validateGetAssocIdentifier=================================");
    		
    	}catch(Exception e){
    		LOGGER.info("Exception is", e);
    	}
    }
    
    @Test
    public void validateGetAssociateIdentifierUnAuthorized(){
    	try{
    		LOGGER.info("==================================Entering into validateGetAssocIdentifier================================");
    		AssocIdentifierRequest request = new AssocIdentifierRequest();
    		AssocIdentifier assocIdentifier = new AssocIdentifier();
    		assocIdentifier.setAssociateId("224222714");
    		assocIdentifier.setIdType(Constants.WALMART_IDENTIFICATION_NUM);
    		request.setAssocIdentifier(assocIdentifier);
    		request.setCountryCode(Constants.COUNTRYCODE_US);
    		
    		URI url = new URI("/assocIdentifier/associate");
    		
			this.mockMvc.perform(
					post(url).with(httpBasic(userName, incUserCred)).content(convertObjectToJsonArray(request)).contentType(MediaType.parseMediaType(APPLN_CHARSET)))
					.andExpect(status().isUnauthorized());
    		
    		
    		LOGGER.info("==================================Exiting from validateGetAssocIdentifier=================================");
    		
    	}catch(Exception e){
    		LOGGER.info("Exception is", e);
    	}
    }
    
    @Test
    public void validateGetAssociateIdentifierNoValidMembers(){
    	try{
    		LOGGER.info("==================================Entering into validateGetAssocIdentifier================================");
    		AssocIdentifierRequest request = new AssocIdentifierRequest();
    		AssocIdentifier assocIdentifier = new AssocIdentifier();
    		assocIdentifier.setAssociateId("224222714");
    		assocIdentifier.setIdType(Constants.WALMART_IDENTIFICATION_NUM);
    		request.setAssocIdentifier(assocIdentifier);
    		request.setCountryCode(Constants.COUNTRYCODE_US);
    		
    		URI url = new URI("/assocIdentifier/associate");
    		
			this.mockMvc.perform(
					post(url).content(convertObjectToJsonArray(request)).contentType(MediaType.parseMediaType(APPLN_CHARSET)))
					.andExpect(status().isBadRequest());
    		
    		
    		LOGGER.info("==================================Exiting from validateGetAssocIdentifier=================================");
    		
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
