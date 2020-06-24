package com.prashant.springboot.springbootrestfulwebservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestTest {

	@GetMapping("/hello")
	public String helloWorld() {
		return "Hello World";
	}

	@GetMapping("/hello/{value}")
	public String helloWorldPathVariable(@PathVariable String value) {
		return "Hello : " + value;
	}
}
