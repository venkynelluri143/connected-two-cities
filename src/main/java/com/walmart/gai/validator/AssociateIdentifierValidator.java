package com.walmart.gai.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.walmart.gai.enumerations.ErrorCodeEnum;
import com.walmart.gai.model.AssocIdentifierRequest;
import com.walmart.gai.util.Constants;

public class AssociateIdentifierValidator implements Validator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AssociateIdentifierValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return AssociateIdentifierValidator.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AssocIdentifierRequest request = (AssocIdentifierRequest)target;
		LOGGER.info("GGA Input Request :" +request);
		
		
		if(request.getCountryCode() == null || request.getCountryCode().isEmpty() || (request.getCountryCode() != null && request.getCountryCode().trim().isEmpty())){
			errors.rejectValue("countryCode", ErrorCodeEnum.GAI_COUNTRY_CODE.getCode(),ErrorCodeEnum.GAI_COUNTRY_CODE.getDescription());
		}
		
		if(request.getAssocIdentifier() == null){
			errors.rejectValue("countryCode", ErrorCodeEnum.GAI_ASSOC_IDENTIFIER.getCode(),ErrorCodeEnum.GAI_ASSOC_IDENTIFIER.getDescription());
		}
		
		if(request.getAssocIdentifier() != null){
			if(request.getAssocIdentifier().getAssociateId() == null || (request.getAssocIdentifier() != null && request.getAssocIdentifier().getAssociateId().isEmpty())){
				errors.rejectValue("assocIdentifier", ErrorCodeEnum.GAI_ASSOC_ID.getCode(),ErrorCodeEnum.GAI_ASSOC_ID.getDescription());
			}
			if(request.getAssocIdentifier().getIdType() == null || (request.getAssocIdentifier().getIdType() != null && request.getAssocIdentifier().getIdType().isEmpty())){
				errors.rejectValue("assocIdentifier", ErrorCodeEnum.GAI_ASSOC_ID.getCode(),ErrorCodeEnum.GAI_ASSOC_ID.getDescription());
			}
			
			if((!request.getAssocIdentifier().getIdType().equalsIgnoreCase(Constants.walmartIdentificationNum)) && (!request.getAssocIdentifier().getIdType().equalsIgnoreCase(Constants.nationalID))){
				errors.rejectValue("assocIdentifier", ErrorCodeEnum.GAI_ASSOC_ID_INVALID.getCode(),ErrorCodeEnum.GAI_ASSOC_ID_INVALID.getDescription());
			}
			
			if(request.getAssocIdentifier().getIdType().equalsIgnoreCase(Constants.walmartIdentificationNum) && (request.getAssocIdentifier().getAssociateId().length() != 9)){
				errors.rejectValue("assocIdentifier", ErrorCodeEnum.GAI_ASSOC_ID_INVALID_LENGTH.getCode(),ErrorCodeEnum.GAI_ASSOC_ID_INVALID_LENGTH.getDescription());
			}
			
		}
		
		// validate length of Id - WIN,SSN - needs to be 9 , UID - length needs to be > 8
		
		//validate IDType - needs to WIN, SSN and UID 
	}

}
