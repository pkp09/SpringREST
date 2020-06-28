package com.prashant.springboot.springbootrestfulwebservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prashant.springboot.springbootrestfulwebservices.pojo.Name;
import com.prashant.springboot.springbootrestfulwebservices.pojo.PersionVersionV1;
import com.prashant.springboot.springbootrestfulwebservices.pojo.PersionVersionV2;

@RestController
public class VersioningController {

	@GetMapping("/v1/name")
	public PersionVersionV1 getV1Name() {
		return new PersionVersionV1("Aarav Pandey");
	}
	
	@GetMapping("/v2/name")
	public PersionVersionV2 getV2Name() {
		return new PersionVersionV2(new Name("Aarav", "Pandey"));
	}
	
	// using Request Param
	@GetMapping(value="person/param", params="version=1")
	public PersionVersionV1 paramV1() {
		return new PersionVersionV1("Aarav Pandey");
	}
	// using Request Param	
	@GetMapping(value="person/header", params="version=2")
	public PersionVersionV2 paramV2() {
		return new PersionVersionV2(new Name("Aarav", "Pandey"));
	}
	
	// using Request Header
	@GetMapping(value="person/header", headers="X_API_VERSION=1")
	public PersionVersionV1 headerV1() {
		return new PersionVersionV1("Aarav Pandey");
	}
	// using Request Header	
	@GetMapping(value="person/header", headers="X_API_VERSION=2")
	public PersionVersionV2 headerV2() {
		return new PersionVersionV2(new Name("Aarav", "Pandey"));
	}
}
