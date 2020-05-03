package com.walmart.gai.enumerations;

public enum ErrorCodeEnum {
	
	GAI_LANG_CODE("CHGAI001","Language Code cannot be Empty"),

	GAI_LANG_CODE_INVALID("CHGAI006","Invalid Lang Code, Supported one ENG:"),
	
	GAI_COUNTRY_CODE("CHGAI001","Country Code cannot be Empty"),
	GAI_ASSOC_IDENTIFIER("CHGAI002","Associate Identifier cannot be Empty"),
	GAI_ASSOC_ID("CHGAI003","Associate ID cannot be Empty"), 
	GAI_ASSOC_IDTYPE("CHGAI004", "Associate ID Type cannot be Empty"),
	GAI_ASSOC_ID_INVALID("CHGAI005", "Asscoiate ID Type needs to be WIN/SSN"),
	GAI_ASSOC_ID_INVALID_LENGTH("CHGAI005", "Associate ID (WIN) needs to be 9 characters to process the request"),
	GAI_NOT_FOUND("CHGAI006","No Data Found for given associate Id , Id Type and Country Code :");

	private String code;
    private String description;

    ErrorCodeEnum(String code, String description) {
	this.code = code;
	this.description = description;
    }

    public String getCode() {
	return code;
    }

    public String getDescription() {
	return description;
    }
}
