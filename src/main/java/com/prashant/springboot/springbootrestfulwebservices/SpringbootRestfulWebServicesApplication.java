package com.prashant.springboot.springbootrestfulwebservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class SpringbootRestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestfulWebServicesApplication.class, args);
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		
		return localeResolver;
	}

	
	@Bean
	public ResourceBundleMessageSource bundleMessageResource() {
		ResourceBundleMessageSource bundleResource = new ResourceBundleMessageSource();
		bundleResource.setBasename("messages");
		return bundleResource;
	}
}
