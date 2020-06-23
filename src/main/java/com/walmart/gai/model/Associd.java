package com.walmart.gai.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Associd implements Serializable {

	private static final long serialVersionUID = 1L;
	
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
