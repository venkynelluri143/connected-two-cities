package com.connect.cities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@ComponentScan(basePackages={"com.connect.cities"})
public class GetCitiesConnected{

	public static void main(String[] args) {
		SpringApplication.run(GetCitiesConnected.class, args);
	}

}
