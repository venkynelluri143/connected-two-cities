package com.walmart.gai.globalFilter;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.validation.FieldError;

import com.jfilter.filter.DynamicFilterComponent;
import com.jfilter.filter.DynamicFilterEvent;
import com.jfilter.filter.FilterFields;
import com.jfilter.request.RequestSession;
import com.walmart.gai.enumerations.ErrorCodeEnum;
import com.walmart.gai.exceptions.BadRequestException;
import com.walmart.gai.model.Associd;
import com.walmart.gai.util.Constants;

@DynamicFilterComponent
public class DemoIdFilter implements DynamicFilterEvent {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoIdFilter.class);
	FilterFields fields = new FilterFields();
	
	@Override
    public FilterFields onGetFilterFields(MethodParameter methodParameter, RequestSession request) {
		LOGGER.info("Session Attribute :"+request.getSession().getAttribute(Constants.groupLevel));
		
		if (request.getSession().getAttribute(Constants.groupLevel).equals(Constants.private1)
				|| request.getSession().getAttribute(Constants.groupLevel).equals(Constants.private2)
				|| request.getSession().getAttribute(Constants.groupLevel).equals(Constants.private3)) {
			//Do nothing
			fields.appendToMap(Associd.class, Arrays.asList(Constants.public_Filter.split(",")));
			//fields.appendToMap(AssociateContactResponse.class, Arrays.asList(Constants.confed1_Filter.split(",")));
            return fields;
            
        }else if (request.getSession().getAttribute(Constants.groupLevel).equals(Constants.confed3)
        		|| request.getSession().getAttribute(Constants.groupLevel).equals(Constants.confed2)
        		|| request.getSession().getAttribute(Constants.groupLevel).equals(Constants.confed1)) {
        	fields.appendToMap(Associd.class, Arrays.asList(Constants.public_Filter.split(",")));
        	
        	return fields;
        } else if (request.getSession().getAttribute(Constants.groupLevel).equals(Constants.public1)
				|| request.getSession().getAttribute(Constants.groupLevel).equals(Constants.public2)
				|| request.getSession().getAttribute(Constants.groupLevel).equals(Constants.public3)) {
			
			fields.appendToMap(Associd.class, Arrays.asList(Constants.public_Filter.split(",")));
        	
        	return fields; 
        }
		else {
        	LOGGER.info("Not a valid LDAP AD group :"+request.getSession().getAttribute(Constants.groupLevel));
			throw new BadRequestException(new FieldError(Constants.processId,ErrorCodeEnum.GGA_PROCESSID_INVALID.getCode(),ErrorCodeEnum.GGA_PROCESSID_INVALID.getDescription()));
        }
	}
}
