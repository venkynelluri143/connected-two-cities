package com.connect.cities.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@PropertySource("classpath:api-document.properties")
public class SwaggerConfig {

    @Value("#{'${api.title}'}")
    private String apiTitle;
    @Value("#{'${api.basePackage}'}")
    private String apiBasePackage;
    @Value("#{'${api.description}'}")
    private String apiDescription;
    @Value("#{'${api.version}'}")
    private String apiVersion;
    @Value("#{'${api.license}'}")
    private String apiLicense;
    @Value("#{'${api.contact.name}'}")
    private String contactName;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public Docket apiDocumentation() {
        return new Docket(DocumentationType.SWAGGER_2).pathMapping("/")
                .select().paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage(apiBasePackage))
                .build().apiInfo(apiInfo());

    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder().title(apiTitle).description(apiDescription)
                .version(apiVersion).license(apiLicense)
                .contact(new Contact(contactName, "N/A", "N/A")).build();
    }
}
