package com.walmart.gga.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.walmart.gai.dao.ApiConsumerKeys;
import com.walmart.gai.dao.WinAssociate;
import com.walmart.gai.dao.repositoryinternational.WinAssociateRepositoryInternational;
import com.walmart.gai.dao.repositorylocal.ApiConsumerKeysRepository;
import com.walmart.gai.dao.repositorylocal.WinAssociateRepository;
import com.walmart.gai.model.AssocIdentifier;
import com.walmart.gai.model.AssocIdentifierRequest;
import com.walmart.gai.model.AssocIdentifierResponse;
import com.walmart.gai.service.AssocIdentifierService;
import com.walmart.gai.util.Constants;
import com.walmart.gbs.corehr.CryptoUtil;


@RunWith(MockitoJUnitRunner.class)
public class AssociateIdentifierServiceTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssociateIdentifierServiceTest.class);
	private String GROUPLEVEL = Constants.PRIVATE1;
	private String COUNTRYCODE = "MX"; 
	private String WINNBR = "224222714";
	private String NATIONALID = "339985070";
	private String WINNBR_MX = "226669310";
	private String NATIONALID_MX = "98119235806";
	private String PROCESS_ID = "TEST_STG";
	private String PUBLIC_KEY= "METREDRFCG";
	
	@InjectMocks
	private AssocIdentifierService assocIdentifierService;
	
	@Mock
	private WinAssociateRepository winAssociateRepository;
	
	@Mock
	private WinAssociateRepositoryInternational winAssociateRepositoryInternational;
	
	@Mock
	private ApiConsumerKeysRepository apiConsumerKeysRepository;
	
	@Mock
	private CryptoUtil cryptoUtil;

	@Before
	public void setUp() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		MockitoAnnotations.initMocks(this);
		ApiConsumerKeys keys= new ApiConsumerKeys();
		keys.setPublicKey(PUBLIC_KEY);
		when(apiConsumerKeysRepository.findByProcessId(Matchers.anyString())).thenReturn(keys);
		
	}
	
	@Test
	public void assocIdentifierServiceTestsWinNumber() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		AssocIdentifierRequest request = new AssocIdentifierRequest();
		request.setCountryCode(Constants.COUNTRYCODE_US);
		AssocIdentifier assocIdentifier = new AssocIdentifier();
		assocIdentifier.setAssociateId(WINNBR);
		assocIdentifier.setIdType(Constants.WALMART_IDENTIFICATION_NUM);
		request.setAssocIdentifier(assocIdentifier);
		
		WinAssociate winAssociate = new WinAssociate();
		winAssociate.setWalmartIdentificationNumber(WINNBR);
		winAssociate.setNationalId(NATIONALID);
		
		when(winAssociateRepository.findByWalmartIdentificationNumberAndStrCountryCode(Matchers.anyString(), Matchers.anyString())).thenReturn(winAssociate);
		
		AssocIdentifierResponse response = assocIdentifierService.assocIdentifierService(request, GROUPLEVEL, PROCESS_ID);
		LOGGER.info("Response :" + response);
		
	}
	
	@Test
	public void assocIdentifierServiceTestsNationalId() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
	
		AssocIdentifierRequest request = new AssocIdentifierRequest();
		request.setCountryCode(Constants.COUNTRYCODE_US);
		AssocIdentifier assocIdentifier = new AssocIdentifier();
		assocIdentifier.setAssociateId(NATIONALID);
		assocIdentifier.setIdType(Constants.NATIONALID);
		request.setAssocIdentifier(assocIdentifier);
		
		WinAssociate winAssociate = new WinAssociate();
		winAssociate.setWalmartIdentificationNumber(WINNBR);
		winAssociate.setNationalId(NATIONALID);
		
	    when(winAssociateRepository.findByNationalIdAndStrCountryCode(Matchers.anyString(), Matchers.anyString())).thenReturn(winAssociate);
		  
		AssocIdentifierResponse response = assocIdentifierService.assocIdentifierService(request, GROUPLEVEL,PROCESS_ID);
		LOGGER.info("Response :" + response);
		
	}
	
	@Test
	public void assocIdentifierServiceTests(){
	
		AssocIdentifierRequest request = new AssocIdentifierRequest();
		request.setCountryCode(Constants.COUNTRYCODE_US);
		AssocIdentifier assocIdentifier = new AssocIdentifier();
		assocIdentifier.setAssociateId(WINNBR);
		assocIdentifier.setIdType(Constants.WALMART_IDENTIFICATION_NUM);
		request.setAssocIdentifier(assocIdentifier);
		
		try {
			assocIdentifierService.assocIdentifierService(request, GROUPLEVEL, PROCESS_ID);
		}catch(Exception e){
			LOGGER.info("No Data Found Exception :" + e);
		}
	}
	
	@Test
	public void assocIdentifierServiceTestsWinNumberInternational() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		AssocIdentifierRequest request = new AssocIdentifierRequest();
		request.setCountryCode(COUNTRYCODE);
		AssocIdentifier assocIdentifier = new AssocIdentifier();
		assocIdentifier.setAssociateId(WINNBR_MX);
		assocIdentifier.setIdType(Constants.WALMART_IDENTIFICATION_NUM);
		request.setAssocIdentifier(assocIdentifier);
		
		WinAssociate winAssociate = new WinAssociate();
		winAssociate.setWalmartIdentificationNumber(WINNBR_MX);
		winAssociate.setNationalId(NATIONALID_MX);
		
	    when(winAssociateRepositoryInternational.findByWalmartIdentificationNumberAndStrCountryCode(Matchers.anyString(),Matchers.anyString())).thenReturn(winAssociate);
		 
		AssocIdentifierResponse response = assocIdentifierService.assocIdentifierService(request, GROUPLEVEL, PROCESS_ID);
		LOGGER.info("Response :" + response);
	}
	
	@Test
	public void assocIdentifierServiceTestsNationalIdInternational() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
	
		AssocIdentifierRequest request = new AssocIdentifierRequest();
		request.setCountryCode(COUNTRYCODE);
		AssocIdentifier assocIdentifier = new AssocIdentifier();
		assocIdentifier.setAssociateId(NATIONALID_MX);
		assocIdentifier.setIdType(Constants.NATIONALID);
		request.setAssocIdentifier(assocIdentifier);
		
		WinAssociate winAssociate = new WinAssociate();
		winAssociate.setWalmartIdentificationNumber(WINNBR_MX);
		winAssociate.setNationalId(NATIONALID_MX);
		
		when(winAssociateRepositoryInternational.findByNationalIdAndStrCountryCode(Matchers.anyString(), Matchers.anyString())).thenReturn(winAssociate);
		
		AssocIdentifierResponse response = assocIdentifierService.assocIdentifierService(request, GROUPLEVEL, PROCESS_ID);
		LOGGER.info("Response :" + response);
	}
}
