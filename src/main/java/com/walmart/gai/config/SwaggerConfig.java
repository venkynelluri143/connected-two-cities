package com.walmart.gai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan("com.walmart")
public class SwaggerConfig {

	@Bean
	public Docket restfulApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Get Associate Identifier Rest Service")  
		          .select()                                  
		          .apis(RequestHandlerSelectors.basePackage("com.walmart.gai.controller"))              
		          .paths(PathSelectors.any())                          
		          .build().apiInfo(apiInfo());  
	}
	
	private ApiInfo apiInfo() {
		ApiInfo apiInfo = null;
		apiInfo = new ApiInfo("Get Associate Identifier","Get Associate Identifier Rest Service","1.0",
				"Link","Core HR Services","License","Link");
		return apiInfo;
	}
	
}
