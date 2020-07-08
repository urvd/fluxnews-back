package com.backend.fluxnewsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@RestController
public class FluxnewsApiApplication {

	@RequestMapping(name = "/", method = RequestMethod.GET)
	public String endpointRoot(){
		String hello = "-- HELLO THE WORLD !!! --";
		/*if(DbConnection.getRemoteConnection() != null){
			hello += "Secure";
		}*/
		return hello;
	}

	public static void main(String[] args) {

		SpringApplication.run(FluxnewsApiApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

}
