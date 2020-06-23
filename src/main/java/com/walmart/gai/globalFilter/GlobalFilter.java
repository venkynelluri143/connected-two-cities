package com.walmart.gai.globalFilter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import com.walmart.gai.enumerations.ErrorCodeEnum;
import com.walmart.gai.exceptions.BadRequestException;
import com.walmart.gai.util.Constants;


@Service
public class GlobalFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalFilter.class);
	
	public String getMemberGroup(List<String> memberList, String countryCode){
	
		List<String> levels = new ArrayList<>();
		levels.add(Constants.private3);
		levels.add(Constants.private2);
		levels.add(Constants.private1);
		levels.add(Constants.confed3);
		levels.add(Constants.confed2);
		levels.add(Constants.confed1);
		levels.add(Constants.public3);
		levels.add(Constants.public2);
		levels.add(Constants.public1);
		
		List<String> groupList = new ArrayList<>();
		
		//CN=WFM-
		Pattern globalPattern = Pattern.compile(Constants.searchPattern);
		
		if(memberList != null && !memberList.isEmpty()){
			for(String member : memberList){
				Matcher globalMatch = globalPattern.matcher(member);
				while(globalMatch.find()) {
					groupList.add(member.substring(globalMatch.end(), member.indexOf(',', globalMatch.end())));
				}
			}
		}else{
			throw new BadRequestException(new FieldError(Constants.processId,ErrorCodeEnum.GGA_PROCESSID_NOMEMBERS.getCode(),ErrorCodeEnum.GGA_PROCESSID_NOMEMBERS.getDescription()));
		}
		
		if(groupList != null & !groupList.isEmpty()){
			//Collections.sort(groupList, Comparator.comparing(item -> levels.indexOf(item)));
			groupList.sort(Comparator.comparingInt(levels::indexOf));
			LOGGER.info("Group List after sorted levels :"+groupList);
			return groupList.get(0);
			
		}else {
			LOGGER.info("No valid LDAP AD groups associated with the given Process id");
			throw new BadRequestException(new FieldError(Constants.processId,ErrorCodeEnum.GGA_PROCESSID_EXCEP.getCode(),ErrorCodeEnum.GGA_PROCESSID_EXCEP.getDescription()));
		}
	}
	
}
