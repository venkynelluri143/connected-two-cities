package com.walmart.gai.validator;

import java.util.ArrayList;
import java.util.List;

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
		List<String> idTypes = new ArrayList<>();
		idTypes.add(Constants.WALMART_IDENTIFICATION_NUM);
		idTypes.add(Constants.NATIONALID);
		
		if(request.getCountryCode() == null  || request.getCountryCode().trim().isEmpty()){
			errors.rejectValue("countryCode", ErrorCodeEnum.GAI_COUNTRY_CODE.getCode(),ErrorCodeEnum.GAI_COUNTRY_CODE.getDescription());
		}
		
		if(request.getAssocIdentifier() != null){
			if (request.getAssocIdentifier().getAssociateId() == null
					|| request.getAssocIdentifier().getAssociateId().trim().isEmpty()) {
				errors.rejectValue(Constants.ASSOCIDENTIFIER, ErrorCodeEnum.GAI_ASSOC_ID.getCode(),
						ErrorCodeEnum.GAI_ASSOC_ID.getDescription());
			}
			if (request.getAssocIdentifier().getIdType() == null
					|| request.getAssocIdentifier().getIdType().trim().isEmpty()) {
				errors.rejectValue(Constants.ASSOCIDENTIFIER, ErrorCodeEnum.GAI_ASSOC_IDTYPE.getCode(),
						ErrorCodeEnum.GAI_ASSOC_IDTYPE.getDescription());
			}

			if (!idTypes.contains(request.getAssocIdentifier().getIdType())) {
				errors.rejectValue(Constants.ASSOCIDENTIFIER, ErrorCodeEnum.GAI_ASSOC_ID_INVALID.getCode(),
						ErrorCodeEnum.GAI_ASSOC_ID_INVALID.getDescription());
			}

			if (request.getAssocIdentifier().getIdType().equalsIgnoreCase(Constants.WALMART_IDENTIFICATION_NUM)
					&& (request.getAssocIdentifier().getAssociateId().length() != 9)) {
				errors.rejectValue(Constants.ASSOCIDENTIFIER, ErrorCodeEnum.GAI_ASSOC_ID_INVALID_LENGTH.getCode(),
						ErrorCodeEnum.GAI_ASSOC_ID_INVALID_LENGTH.getDescription());
			}
			
			} else {
				errors.rejectValue(Constants.ASSOCIDENTIFIER, ErrorCodeEnum.GAI_ASSOC_IDENTIFIER.getCode(),ErrorCodeEnum.GAI_ASSOC_IDENTIFIER.getDescription());
			}	
	}

}
