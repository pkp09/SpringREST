package com.prashant.springboot.springbootrestfulwebservices.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.prashant.springboot.springbootrestfulwebservices.pojo.DynamicFilterPojo;
import com.prashant.springboot.springbootrestfulwebservices.pojo.StaticFilterPojo;
import com.prashant.springboot.springbootrestfulwebservices.util.FilterUtil;

@RestController
public class FilterController {

	@GetMapping("/static-filter")
	public StaticFilterPojo saticFiltering() {
		return new StaticFilterPojo("value1", "value2", "value3", "value4");
	}
	
	@GetMapping("/static-filter-list")
	public List<StaticFilterPojo> saticFilterList() {
		List<StaticFilterPojo> list = Arrays.asList(new StaticFilterPojo("value1", "value2", "value3", "value4"),
				new StaticFilterPojo("value12", "value22", "value32", "value42"));
		return list;
	}
	
	@GetMapping("/dynamic-filter")
	public MappingJacksonValue dynamicFiltering() {
		DynamicFilterPojo dfc =	new DynamicFilterPojo("value1", "value2", "value3", "value4");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("value2", "value3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("DynamicFiltering", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(dfc);
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	@GetMapping("/dynamic-filter-list")
	public MappingJacksonValue dynamicFilteringList() {
		List<DynamicFilterPojo> dynamicFilterbean =	Arrays.asList(
				new DynamicFilterPojo("value1", "value2", "value3", "value4"),
				new DynamicFilterPojo("value12", "value22", "value32", "value42"));
		String[] str = new String[]{"value1","value4"};
		MappingJacksonValue mapping = FilterUtil.dynamicFilter(dynamicFilterbean, str);
		
		return mapping;
	}

	
}
