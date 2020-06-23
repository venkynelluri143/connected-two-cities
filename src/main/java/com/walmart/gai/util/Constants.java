package com.walmart.gai.util;

public interface Constants {
	public static final String YES = "YES";
	public static final int MaxTries = 3;
	public static final String operationChannel = "DFHWS-OPERATION";
	public static final String dataChannel = "DFHWS-DATA";		
	public static final String LOG_PROPERTIES_FILE = "/hrserviceslog4j.properties";
	
	public static final String walmartIdentificationNum = "WIN";
	public static final String nationalID = "SSN";
	public static final String userId = "UID";
	public static final String storeAscInd_N = "N";
	public static final String storeAscInd_Y = "Y";
	public static final String hrSourceCode_3 = "3";
	
	public static final String associateGeographicCategoryCode_FL = "FL";
	public static final String associateGeographicCategoryDesc_FL = "FIELD";
	public static final String associateGeographicCategoryCode_DC = "DC";
	public static final String associateGeographicCategoryDesc_DC = "LOGISTICS";
	public static final String associateGeographicCategoryCode_HO = "HO";
	public static final String associateGeographicCategoryDesc_HO = "HOME OFFICE";
	
	public static final String languageCode = "ENG";
	public static final Integer langCode = new Integer ("101");
	
	public static final String countryCode_US = "US";
	public static final String countryCode_CA = "CA";
	
	public static final String rehireEligible_YES = "Y";
	public static final String rehireEligible_NO = "N";
	public static final String historyTermCode_108 = "108";
	public static final String historyTermCode_108_Desc = "Settlement issues";
	
	public static final String searchPattern = "CN=WFM-";
	public static final String private1 = "PRIVATE-1";
	public static final String private2 = "PRIVATE-2";
	public static final String private3 = "PRIVATE-3";
	public static final String confed1 = "CONFED-1";
	public static final String confed2 = "CONFED-2";
	public static final String confed3 = "CONFED-3";
	public static final String public1 = "PUBLIC-1";
	public static final String public2 = "PUBLIC-2";
	public static final String public3 = "PUBLIC-3";
	
	public static final String Authorization = "Authorization";
	public static final String ContentType = "application/json";
	public static final String AuthenticationMessage = "Full Authentication is required to access this resource";
	public static final String groupLevel = "GroupLevel";
	public static final String memberOf = "memberOf";
	public static final String processId = "ProcessId";
	
	public static final String confed_Filter = "associdtype,associdentifierValue";
	public static final String public_Filter = "associd";
	
}
