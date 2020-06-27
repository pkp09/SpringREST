package com.prashant.springboot.springbootrestfulwebservices.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestTest {
	@Autowired
	MessageSource messageSource;

	@GetMapping("/hello")
	public String helloWorld() {
		return "Hello World";
	}

	@GetMapping("/hello/{value}")
	public String helloWorldPathVariable(@PathVariable String value) {
		return "Hello : " + value;
	}
	
	@GetMapping("/hello/i18n")
	public String helloWorldInternationalization(@RequestHeader(name="Accept-Language", required=true) Locale locale) {
		
		return messageSource.getMessage("good.morning.message", null, locale);
	}
	
	
	@GetMapping("/hello/i18n/v2")
	public String helloWorldInternationalizationV2() {
		
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
}
