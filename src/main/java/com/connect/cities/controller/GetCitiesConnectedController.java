package com.walmart.gai.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.gai.globalfilter.GlobalFilter;
import com.walmart.gai.model.AssocIdentifierRequest;
import com.walmart.gai.model.AssocIdentifierResponse;
import com.walmart.gai.service.AssocIdentifierService;
import com.walmart.gai.validator.AssociateIdentifierValidator;

@RestController
@RequestMapping(path = "/assocIdentifier")
public class AssocIdentifierController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AssocIdentifierController.class);
	
	@Autowired
	private AssocIdentifierService assocIdentifierService; 
	
	@Autowired
	private AssociateIdentifierValidator getAssociateIdentifierValidator;
	
	@Autowired
	private GlobalFilter globalFilter;
	
	@RequestMapping(value = "/associate", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public AssocIdentifierResponse getAssocIdentifier(@RequestBody AssocIdentifierRequest assocIdentifierRequest,BindingResult errors) throws BindException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		
		LOGGER.info("Input Request :" + assocIdentifierRequest);
		// validate Input request - AssocIdentifierRequest
		getAssociateIdentifierValidator.validate(assocIdentifierRequest, errors);
		if (errors.hasErrors()) {
			throw new BindException(errors);
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		LOGGER.info("User :"+authentication.getName());
		
		String groupLevel = globalFilter.getMemberGroup(authentication);
		LOGGER.info("Group Level :"+groupLevel);
		
		return assocIdentifierService.assocIdentifierService(assocIdentifierRequest, groupLevel, authentication.getName()); 		
	}
	
	@RequestMapping(value="/healthCheck", method = RequestMethod.GET, produces="application/json") 
	public ResponseEntity<String> healthCheck(){
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}
}
