package com.backend.fluxnewsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FluxnewsApiApplication {

	public static void main(String[] args) {
		//DbConnection dbConnection = new DbConnection();
		SpringApplication.run(FluxnewsApiApplication.class, args);
	}

}
