package com.walmart.gai.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.javers.core.metamodel.annotation.TypeName;


@Entity
@Table(name = "WIN_ASSOCIATE")
@TypeName("WinAssociate")
public class WinAssociate implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "WIN_NBR")
    private String walmartIdentificationNumber;
	
	@Column(name = "STR_COUNTRY_CODE")
    private String strCountryCode;
	
	@Column(name = "NATIONAL_ID")
	private String nationalId;

	public String getWalmartIdentificationNumber() {
		return walmartIdentificationNumber;
	}
	
	public String getStrCountryCode() {
		return strCountryCode;
	}

	public void setStrCountryCode(String strCountryCode) {
		this.strCountryCode = strCountryCode;
	}

	public String getNationalId() {
		return nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}



	public void setWalmartIdentificationNumber(String walmartIdentificationNumber) {
		this.walmartIdentificationNumber = walmartIdentificationNumber;
	}



	@Override
	public String toString() {
		return "WinAssociate [walmartIdentificationNumber=" + walmartIdentificationNumber + ", strCountryCode="
				+ strCountryCode + ", nationalId=" + nationalId + "]";
	}
	
}
