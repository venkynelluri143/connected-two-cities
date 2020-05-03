package com.walmart.gai.model;

public class AssocIdentifierRequest {
	
	private AssocIdentifier assocIdentifier;
	private String countryCode;
	
	public AssocIdentifier getAssocIdentifier() {
		return assocIdentifier;
	}
	public void setAssocIdentifier(AssocIdentifier assocIdentifier) {
		this.assocIdentifier = assocIdentifier;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	@Override
	public String toString() {
		return "AssocIdentifierRequest [assocIdentifier=" + assocIdentifier + ", countryCode=" + countryCode + "]";
	}
	
}
