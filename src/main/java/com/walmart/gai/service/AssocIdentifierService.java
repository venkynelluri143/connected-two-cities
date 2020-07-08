package com.walmart.gai.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walmart.gai.dao.WinAssociate;
import com.walmart.gai.dao.repositoryinternational.WinAssociateRepositoryInternational;
import com.walmart.gai.dao.repositorylocal.WinAssociateRepository;
import com.walmart.gai.enumerations.ErrorCodeEnum;
import com.walmart.gai.exceptions.DataNotFoundException;
import com.walmart.gai.model.AssocIdentifierRequest;
import com.walmart.gai.model.AssocIdentifierResponse;
import com.walmart.gai.model.Associd;
import com.walmart.gai.model.Associds;
import com.walmart.gai.util.Constants;

@Service
public class AssocIdentifierService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AssocIdentifierService.class);
	
	@Autowired
	private WinAssociateRepository winAssociateRepository;
	
	@Autowired
	private WinAssociateRepositoryInternational winAssociateRepositoryInternational;
	
	public AssocIdentifierResponse assocIdentifierService(AssocIdentifierRequest assocIdentifierRequest, String groupLevel){
		Boolean isGlobal = true;
		String id = assocIdentifierRequest.getAssocIdentifier().getAssociateId().trim(); //value 
		String idType = assocIdentifierRequest.getAssocIdentifier().getIdType().trim(); //SSN , WIN 
		String countryCode = assocIdentifierRequest.getCountryCode();
		
		if (countryCode.equalsIgnoreCase(Constants.COUNTRYCODE_US)
				|| countryCode.equalsIgnoreCase(Constants.COUNTRYCODE_CA)) {
			isGlobal = false;
		}
		
		WinAssociate winAssociate = null;
		if(isGlobal){
			winAssociate = getAssocIdentifierInternational(id, idType, countryCode);
		}else {
			winAssociate = getAssocIdentifierLocal(id, idType, countryCode);
		}
		
		LOGGER.info("Win Associate :" +winAssociate);
		if (winAssociate != null) {
			return assocIdentifierResponse(winAssociate, countryCode, groupLevel);
		}else {
			LOGGER.info("No Data Found for given ID :"+id+" and ID type :"+idType);
			throw new DataNotFoundException(ErrorCodeEnum.GAI_NOT_FOUND.getCode(),ErrorCodeEnum.GAI_NOT_FOUND.getDescription()+id+" ,"+idType+" ,"+countryCode);
		}
	}
	
	public AssocIdentifierResponse assocIdentifierResponse(WinAssociate winAssociate, String countryCode, String groupLevel){
		List<String> confedLevels = new ArrayList<>();
		confedLevels.add(Constants.CONFED3);
		confedLevels.add(Constants.CONFED2);
		confedLevels.add(Constants.CONFED1);
		
		List<String> publicLevels = new ArrayList<>();
		publicLevels.add(Constants.PUBLIC1);
		publicLevels.add(Constants.PUBLIC2);
		publicLevels.add(Constants.PUBLIC3);
		
		AssocIdentifierResponse response = new AssocIdentifierResponse(); 
		Associds assocIds = new Associds();
		List<Associd> associdList = new ArrayList<>(); 
		
		if(!publicLevels.contains(groupLevel)){
			Associd assocWin = new Associd();
			assocWin.setAssocidentifierValue(winAssociate.getWalmartIdentificationNumber()!=null ? winAssociate.getWalmartIdentificationNumber().trim() : "");
			assocWin.setAssocidtype(Constants.WALMART_IDENTIFICATION_NUM);
			associdList.add(assocWin);
		}
		
		if(!confedLevels.contains(groupLevel) && !publicLevels.contains(groupLevel)){
			Associd assocSsn = new Associd(); 
			assocSsn.setAssocidentifierValue(winAssociate.getNationalId() != null ? winAssociate.getNationalId().trim() : "");
			assocSsn.setAssocidtype(Constants.NATIONALID);
			associdList.add(assocSsn);
		}
		
		assocIds.setAssocid(associdList);
		response.setAssocids(assocIds);
		response.setCountryCode(countryCode);
		return response; 
	}

	public WinAssociate getAssocIdentifierLocal(String id, String idType, String countryCode){
		WinAssociate winAssociate = new WinAssociate();
		if (idType.equals(Constants.WALMART_IDENTIFICATION_NUM))
			winAssociate = winAssociateRepository.findByWalmartIdentificationNumberAndStrCountryCode(id,
					countryCode);
		else if (idType.equals(Constants.NATIONALID)) 
			winAssociate = winAssociateRepository.findByNationalIdAndStrCountryCode(id, countryCode);
		
		return winAssociate;
	}
	
	public WinAssociate getAssocIdentifierInternational(String id, String idType, String countryCode){
		WinAssociate winAssociate = new WinAssociate();
		if (idType.equals(Constants.WALMART_IDENTIFICATION_NUM))
			winAssociate = winAssociateRepositoryInternational.findByWalmartIdentificationNumberAndStrCountryCode(id,
					countryCode);
		else if (idType.equals(Constants.NATIONALID))
			winAssociate = winAssociateRepositoryInternational.findByNationalIdAndStrCountryCode(id, countryCode);
		
		return winAssociate; 
	}
}
