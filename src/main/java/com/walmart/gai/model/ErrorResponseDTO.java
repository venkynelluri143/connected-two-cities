package com.walmart.gai.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ErrorResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Date time;
	public HttpStatus httpStatus;
	public String code;
	public String message;
	public String object;
	public List result =  new ArrayList<>();

	public ErrorResponseDTO(HttpStatus httpStatus, String code, String message,
			String object) {
		this.httpStatus = httpStatus;
		this.code = code;
		this.message = message;
		this.object = object;
		this.time = new Date();

	}
	
	public ErrorResponseDTO(HttpStatus httpStatus, String code, String message,
			String object, List result) {
		this.httpStatus = httpStatus;
		this.code = code;
		this.message = message;
		this.object = object;
		this.time = new Date();
		this.result = result;
	}

	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}
	
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getObject() {
		return this.object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public List getResult() {
		return result;
	}

	public void setResult(List result) {
		this.result = result;
	}

}
