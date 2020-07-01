package com.walmart.gai.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.http.HttpStatus;

public class ErrorResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Date time;
	private HttpStatus httpStatus;
	private String code;
	private String message;
	private String object;

	public ErrorResponseDTO(HttpStatus httpStatus, String code, String message,
			String object) {
		this.httpStatus = httpStatus;
		this.code = code;
		this.message = message;
		this.object = object;
		this.time = new Date();

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
}
