package com.ps.main;

import org.jboss.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@EnableJpaAuditing
@SpringBootApplication
//@EnableJpaRepositories(basePackages = "spring.data.jpa.stored.procedure.repository")
public class Application {

	static Logger logger = Logger.getLogger(Application.class);

	public static void main(String[] args) {

		// Start-Spring-Boot-Application
		SpringApplication.run(Application.class, args);
		logger.info("Application Started Sucessfully");
	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.ps.RESTful"))
				// .paths(regex("/query-header.*"))
				.build().apiInfo(metaData());
	}

	private ApiInfo metaData() {
		ApiInfo info = new ApiInfo("Query Module", "SpringBoot REST API for Query Module", "1.0",
				"https://ehrms.paysquare.com", "help@paysquare.com", "License Version 2.0", "https://google.com");
		return info;
	}

}
