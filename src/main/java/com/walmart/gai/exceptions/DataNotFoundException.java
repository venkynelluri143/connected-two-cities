package com.walmart.gai.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such data")
public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3992797190692194686L;
	
	private final String code;
	private final String description;
	
	public DataNotFoundException(String code, String description){
		super(code + " " + description);
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
