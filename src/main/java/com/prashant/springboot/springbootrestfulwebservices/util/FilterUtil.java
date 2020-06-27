package com.prashant.springboot.springbootrestfulwebservices.util;

import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.prashant.springboot.springbootrestfulwebservices.pojo.DynamicFilterPojo;

public class FilterUtil {
	
	public static MappingJacksonValue dynamicFilter(List<DynamicFilterPojo> dynamicFilterbean, String... str) {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(str[0], str[1]);
		FilterProvider filters = new SimpleFilterProvider().addFilter("DynamicFiltering", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(dynamicFilterbean);
		mapping.setFilters(filters);
		return mapping;
	}

}
