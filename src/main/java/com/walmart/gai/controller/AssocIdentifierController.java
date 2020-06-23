package com.walmart.gai.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jfilter.filter.DynamicFilter;
import com.walmart.gai.exceptions.ServiceException;
import com.walmart.gai.globalFilter.DemoIdFilter;
import com.walmart.gai.globalFilter.GlobalFilter;
import com.walmart.gai.model.AssocIdentifierRequest;
import com.walmart.gai.model.AssocIdentifierResponse;
import com.walmart.gai.security.ADUserDetailsContextMapper;
import com.walmart.gai.service.AssocIdentifierService;
import com.walmart.gai.util.Constants;
import com.walmart.gai.validator.AssociateIdentifierValidator;

@RestController
@RequestMapping(path = "/assocIdentifier")
public class AssocIdentifierController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AssocIdentifierController.class);
	
	@Autowired
	AssocIdentifierService assocIdentifierService; 
	
	@Autowired
	AssociateIdentifierValidator getAssociateIdentifierValidator;
	
	@Autowired
	ADUserDetailsContextMapper aDUserDetailsContextMapper;
	
	@Autowired
	GlobalFilter globalFilter;
	
	@DynamicFilter(DemoIdFilter.class)
	@RequestMapping(value = "/associate", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public AssocIdentifierResponse getAssocIdentifier(@RequestBody AssocIdentifierRequest assocIdentifierRequest,BindingResult errors, HttpServletRequest request) throws ServiceException, BindException {
		
		AssocIdentifierResponse response = new AssocIdentifierResponse();
		List<String> memberGroups = aDUserDetailsContextMapper.getMemberGroups();
		String groupLevel = globalFilter.getMemberGroup(memberGroups, assocIdentifierRequest.getCountryCode());
		LOGGER.info("Group Level :"+groupLevel+"Member Groups :"+memberGroups);
		request.getSession().setAttribute(Constants.groupLevel, groupLevel);
		
		// validate Input request - AssocIdentifierRequest 
		getAssociateIdentifierValidator.validate(assocIdentifierRequest, errors);
		if(errors.hasErrors()){
			throw new BindException(errors);
		}
		LOGGER.info("Input Request :" + assocIdentifierRequest);
		response = assocIdentifierService.assocIdentifierService(assocIdentifierRequest); 
		return response;
	}
	
	@RequestMapping(value="/healthCheck", method = RequestMethod.GET, produces="application/json") 
	public ResponseEntity<String> healthCheck(){
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}
}
