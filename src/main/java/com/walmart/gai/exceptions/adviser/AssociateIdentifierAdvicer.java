package com.walmart.gai.exceptions.adviser;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.walmart.gai.exceptions.BadRequestException;
import com.walmart.gai.exceptions.DataNotFoundException;
import com.walmart.gai.model.ErrorResponseDTO;

@ControllerAdvice
public class AssociateIdentifierAdvicer {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public List<ErrorResponseDTO> handleMethodArgumentNotValid(HttpServletRequest req, BindException ex) {

	List<ObjectError> errors = ex.getBindingResult().getAllErrors();
	List<ErrorResponseDTO> errorList = new ArrayList<>();

	for (ObjectError e : errors) {
	    if (e instanceof FieldError) {
		FieldError fe = (FieldError) e;
		errorList.add(new ErrorResponseDTO(HttpStatus.BAD_REQUEST, e.getCode(), fe.getField() + " " + e.getDefaultMessage(), req.getRequestURL().toString()));
	    } else {
		errorList.add(new ErrorResponseDTO(HttpStatus.BAD_REQUEST, e.getCode(), e.getDefaultMessage(), req.getRemoteHost()));
	    }

	}
	return errorList;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseBody
    public List<ErrorResponseDTO> handleMethodArgumentNotValid(HttpServletRequest req, DataNotFoundException e) {

	List<ErrorResponseDTO> errorList = new ArrayList<>();
	errorList.add(new ErrorResponseDTO(HttpStatus.NOT_FOUND, e.getCode(), e.getDescription(), "<"+req.getRequestURI()+">"));

	return errorList;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    public List<ErrorResponseDTO> handleMethodArgumentNotValid(HttpServletRequest req, BadRequestException e) {

	List<ErrorResponseDTO> errorList = new ArrayList<>();
	errorList.add(new ErrorResponseDTO(HttpStatus.BAD_REQUEST, e.getError().getCode(), e.getError().getDefaultMessage(), req.getRemoteAddr()));
	return errorList;
    }

}
