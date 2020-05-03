package com.walmart.gai.dao.repositoryLocal;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.walmart.gai.dao.WinAssociate;

@Transactional
@Repository
public interface WinAssociateRepository extends JpaRepository <WinAssociate, Integer>{
	  
	WinAssociate findByWalmartIdentificationNumberAndStrCountryCode(String winNbr, String strCountryCode);
	
	WinAssociate findByNationalIdAndStrCountryCode(String nationalId, String strCountryCode);
	
}
