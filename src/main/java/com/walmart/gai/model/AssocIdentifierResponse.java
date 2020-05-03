package com.walmart.gai.model;

public class AssocIdentifierResponse {

	private Associds  associds ; 
	private String countryCode;
	
	public Associds getAssocids() {
		return associds;
	}
	public void setAssocids(Associds associds) {
		this.associds = associds;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	@Override
	public String toString() {
		return "AssocIdentifierResponse [associds=" + associds + ", countryCode=" + countryCode + "]";
	} 
	
	
	
}
