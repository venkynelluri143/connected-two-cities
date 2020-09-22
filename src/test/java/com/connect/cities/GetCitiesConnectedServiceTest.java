package com.connect.cities;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.connect.cities.service.GetCitiesConnectedService;

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

@RunWith(MockitoJUnitRunner.class)
public class GetCitiesConnectedServiceTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(GetCitiesConnectedServiceTest.class);
	
	@InjectMocks
	private GetCitiesConnectedService assocIdentifierService;


	@Before
	public void setUp() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
		MockitoAnnotations.initMocks(this);		
	}
	
	@Test
	public void testCitiesConnectedYes() throws IOException {
		LOGGER.info("Entering Cities Connected : Valid :");
		assocIdentifierService.getCitiesConnected("Boston", "Newark");
		LOGGER.info("Existing Cities Connected : Valid :");
	}
	
	@Test
	public void testCitiesConnectedNo() throws IOException {
		LOGGER.info("Entering Cities Connected : Invalid :");
		assocIdentifierService.getCitiesConnected("Philadelphia", "Albany");
		LOGGER.info("Existing Cities Connected : Invalid :");
	}
}
