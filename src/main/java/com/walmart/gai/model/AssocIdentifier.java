package com.walmart.gai.model;

public class AssocIdentifier {

	private String associateId; 
	private String idType;
	
	public String getAssociateId() {
		return associateId;
	}
	public void setAssociateId(String associateId) {
		this.associateId = associateId;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	
	@Override
	public String toString() {
		return "AssocIdentifier [associateId=" + associateId + ", idType=" + idType + "]";
	}
	
	
}
