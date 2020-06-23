package com.walmart.gai.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Associds implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Associd> associd;

	public List<Associd> getAssocid() {
		return associd;
	}

	public void setAssocid(List<Associd> associd) {
		this.associd = associd;
	}

	@Override
	public String toString() {
		return "Associds [associd=" + associd + "]";
	} 
	
}
