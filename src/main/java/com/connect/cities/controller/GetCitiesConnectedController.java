package com.connect.cities.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.connect.cities.service.GetCitiesConnectedService;

@RestController
@RequestMapping(path = "/connected")
public class GetCitiesConnectedController {
	private static final Logger LOGGER = LoggerFactory.getLogger(GetCitiesConnectedController.class);
	
	@Autowired
	private GetCitiesConnectedService getCitiesConnectedService; 
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public String getAssocIdentifier(@RequestParam String origin,@RequestParam String destination) throws IOException {
		LOGGER.info("Given Input Cities :{}, {}" + origin,destination);
		return getCitiesConnectedService.getCitiesConnected(origin,destination);
		
	}
}
