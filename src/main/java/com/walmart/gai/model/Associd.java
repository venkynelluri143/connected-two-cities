package com.walmart.gai.model;

public class Associd {
	
	private String associdentifierValue;
	private String associdtype;
	
	public String getAssocidentifierValue() {
		return associdentifierValue;
	}
	public void setAssocidentifierValue(String associdentifierValue) {
		this.associdentifierValue = associdentifierValue;
	}
	public String getAssocidtype() {
		return associdtype;
	}
	public void setAssocidtype(String associdtype) {
		this.associdtype = associdtype;
	}
	
	@Override
	public String toString() {
		return "Associd [associdentifierValue=" + associdentifierValue + ", associdtype=" + associdtype + "]";
	}
	
	
}
