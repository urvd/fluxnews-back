package com.backend.fluxnewsapi;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

/*	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.setInitParameter(
				"spring.profiles.active", "dev");
	}*/

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FluxnewsApiApplication.class);
	}

}
