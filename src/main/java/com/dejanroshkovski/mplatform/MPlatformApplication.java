package com.dejanroshkovski.mplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MPlatformApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(MPlatformApplication.class, args);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		return builder.sources(MPlatformApplication.class);
	}

}
