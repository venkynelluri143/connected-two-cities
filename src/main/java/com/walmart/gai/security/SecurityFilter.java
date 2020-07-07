package com.walmart.gai.security;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walmart.gai.model.ErrorResponseDTO;
import com.walmart.gai.util.Constants;

public class SecurityFilter extends GenericFilterBean{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityFilter.class);
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
			
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			String authorization = req.getHeader(Constants.AUTHORIZATION);
			LOGGER.info("Authorization in the request  :" +authorization);
			if(authorization != null 
					|| req.getRequestURI().contains("/health") 
					|| req.getRequestURI().contains("/swagger")){
				if (req.isSecure())
					resp.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
				chain.doFilter(request, response);
			}else {
				((HttpServletResponse) response).setStatus(HttpStatus.UNAUTHORIZED.value());
				((HttpServletResponse) response).setContentType(Constants.CONTENTTYPE);
				ErrorResponseDTO errorResponse = new ErrorResponseDTO(HttpStatus.UNAUTHORIZED,String.valueOf(HttpStatus.UNAUTHORIZED.value()),Constants.AUTHENTICATIONMESSAGE, req.getContextPath());
		        PrintWriter out = response.getWriter();
		        out.print(objectMapper.writeValueAsString(errorResponse));
		        out.flush();
			}
			
	}
}
