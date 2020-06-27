package com.prashant.springboot.springbootrestfulwebservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication
public class SpringbootRestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestfulWebServicesApplication.class, args);
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		// SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		
		return localeResolver;
	}

	
	// Base name is defined in application.properties file
//	@Bean
//	public ResourceBundleMessageSource bundleMessageResource() {
//		ResourceBundleMessageSource bundleResource = new ResourceBundleMessageSource();
//		bundleResource.setBasename("messages");
//		return bundleResource;
//	}
}
