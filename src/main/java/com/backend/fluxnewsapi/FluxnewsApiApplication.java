package com.backend.fluxnewsapi;

import com.backend.fluxnewsapi.config.DbConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class FluxnewsApiApplication {

	@Autowired
	DbConnection dbConnection;

	public static void main(String[] args) {
		SpringApplication.run(FluxnewsApiApplication.class, args);
	}

}
