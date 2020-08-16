package com.walmart.gai.util;

public class Constants {
	
	private Constants(){}
	
	public static final String YES = "YES";
	public static final int MAXTRIES = 3;
	public static final String OPERATIONCHANNEL = "DFHWS-OPERATION";
	public static final String DATACHANNEL = "DFHWS-DATA";		
	public static final String LOG_PROPERTIES_FILE = "/hrserviceslog4j.properties";
	
	public static final String WALMART_IDENTIFICATION_NUM = "WIN";
	public static final String NATIONALID = "SSN";
	public static final String USERID = "UID";
	public static final String STOREASCIND_N = "N";
	public static final String STOREASCIND_Y = "Y";
	public static final String HRSOURCECODE_3 = "3";
	
	public static final String ASSOCIATEGEOGRAPHICCATEGORYCODE_FL = "FL";
	public static final String ASSOCIATEGEOGRAPHICCATEGORYDESC_FL = "FIELD";
	public static final String ASSOCIATEGEOGRAPHICCATEGORYCODE_DC = "DC";
	public static final String ASSOCIATEGEOGRAPHICCATEGORYDESC_DC = "LOGISTICS";
	public static final String ASSOCIATEGEOGRAPHICCATEGORYCODE_HO = "HO";
	public static final String ASSOCIATEGEOGRAPHICCATEGORYDESC_HO = "HOME OFFICE";
	
	public static final String LANGUAGECODE = "ENG";
	public static final Integer LANGCODE = new Integer ("101");
	
	public static final String COUNTRYCODE_US = "US";
	public static final String COUNTRYCODE_CA = "CA";
	
	public static final String REHIREELIGIBLE_YES = "Y";
	public static final String REHIREELIGIBLE_NO = "N";
	public static final String HISTORYTERMCODE_108 = "108";
	public static final String HISTORYTERMCODE_108_DESC = "Settlement issues";
	public static final String ASSOCIDENTIFIER = "assocIdentifier";
	public static final String PEMFORMAT = "PEM";
	public static final String BINFORMAT = "BIN";
	
	public static final String SEARCHPATTERN = "CN=WFM-";
	public static final String PRIVATE1 = "PRIVATE-1";
	public static final String PRIVATE2 = "PRIVATE-2";
	public static final String PRIVATE3 = "PRIVATE-3";
	public static final String CONFED1 = "CONFED-1";
	public static final String CONFED2 = "CONFED-2";
	public static final String CONFED3 = "CONFED-3";
	public static final String PUBLIC1 = "PUBLIC-1";
	public static final String PUBLIC2 = "PUBLIC-2";
	public static final String PUBLIC3 = "PUBLIC-3";
	
	public static final String USERNAME = "username";
	public static final String USERCRED = "credential";
	public static final String SVCUSERNAME = "svcUsername";
	public static final String SVCUSERCRED = "svcPassword";
	public static final String SVCUSERCREDINCO = "svcIncPassword";
	
	public static final String TRUSTSTORETYPE = "sslTrustStoreType";
	public static final String TRUSTSTORECRED = "sslTruststorePwd";
	public static final String KEYSTORETYPE = "sslKeyStoreType";
	public static final String KEYSTORECRED = "sslKeyStorePassword";
	
	public static final String AUTHORIZATION = "Authorization";
	public static final String CONTENTTYPE = "application/json";
	public static final String AUTHENTICATIONMESSAGE = "Full Authentication is required to access this resource";
	public static final String GROUPLEVEL = "GroupLevel";
	public static final String MEMBEROF = "memberOf";
	public static final String PROCESSID = "ProcessId";
	
	public static final String CONFED_FILTER = "associdtype,associdentifierValue";
	public static final String PUBLIC_FILTER = "associd";
	
}
