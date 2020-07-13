package com.walmart.gai.globalfilter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import com.walmart.gai.enumerations.ErrorCodeEnum;
import com.walmart.gai.exceptions.BadRequestException;
import com.walmart.gai.util.Constants;

@Service
public class GlobalFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalFilter.class);
	
	public String getMemberGroup(Authentication authentication){
		
		List<String> memberList = new ArrayList<>();
		if (authentication != null && authentication.getAuthorities() != null) {
			memberList = authentication.getAuthorities().stream().map(
                    authority -> authority.getAuthority()).collect(Collectors.toList());
        } 
		LOGGER.info("Member List :{}", memberList);
	
		List<String> levels = new ArrayList<>();
		levels.add(Constants.PRIVATE3);
		levels.add(Constants.PRIVATE2);
		levels.add(Constants.PRIVATE1);
		levels.add(Constants.CONFED3);
		levels.add(Constants.CONFED2);
		levels.add(Constants.CONFED1);
		levels.add(Constants.PUBLIC3);
		levels.add(Constants.PUBLIC2);
		levels.add(Constants.PUBLIC1);
		
		List<String> groupList = new ArrayList<>();
		
		//CN=WFM-
		Pattern globalPattern = Pattern.compile(Constants.SEARCHPATTERN);
		
		if(memberList != null && !memberList.isEmpty()){
			for(String member : memberList){
				Matcher globalMatch = globalPattern.matcher(member);
				while(globalMatch.find()) {
					groupList.add(member.substring(globalMatch.end(), member.indexOf(',', globalMatch.end())));
				}
			}
		}else{
			throw new BadRequestException(new FieldError(Constants.PROCESSID,ErrorCodeEnum.GGA_PROCESSID_NOMEMBERS.getCode(),ErrorCodeEnum.GGA_PROCESSID_NOMEMBERS.getDescription()));
		}
		
		if(!groupList.isEmpty()){
			groupList.sort(Comparator.comparingInt(levels::indexOf));
			LOGGER.info("Group List after sorted levels :"+groupList);
			return groupList.get(0);
			
		}else {
			LOGGER.info("No valid LDAP AD groups associated with the given Process id");
			throw new BadRequestException(new FieldError(Constants.PROCESSID,ErrorCodeEnum.GGA_PROCESSID_EXCEP.getCode(),ErrorCodeEnum.GGA_PROCESSID_EXCEP.getDescription()));
		}
	}
}
